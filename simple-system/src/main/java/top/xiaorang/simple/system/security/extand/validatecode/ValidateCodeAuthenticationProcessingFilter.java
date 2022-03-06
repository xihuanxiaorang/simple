package top.xiaorang.simple.system.security.extand.validatecode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.system.exception.MyAuthenticationException;
import top.xiaorang.simple.system.security.SecurityConstant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 该过滤器用于验证码校验（目前表单登录和手机登录时需要校验） */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
public class ValidateCodeAuthenticationProcessingFilter extends OncePerRequestFilter {
  private RequestMatcher requiresAuthenticationRequestMatcher;
  private ValidateCodeProcessorHolder validateCodeProcessorHolder;
  private AuthenticationFailureHandler authenticationFailureHandler;

  public ValidateCodeAuthenticationProcessingFilter(
      RequestMatcher requiresAuthenticationRequestMatcher,
      ValidateCodeProcessorHolder validateCodeProcessorHolder,
      AuthenticationFailureHandler authenticationFailureHandler) {
    Assert.notNull(
        requiresAuthenticationRequestMatcher,
        "requiresAuthenticationRequestMatcher cannot be null");
    Assert.notNull(validateCodeProcessorHolder, "validateCodeProcessorHolder cannot be null");
    Assert.notNull(authenticationFailureHandler, "authenticationFailureHandler cannot be null");
    this.requiresAuthenticationRequestMatcher = requiresAuthenticationRequestMatcher;
    this.validateCodeProcessorHolder = validateCodeProcessorHolder;
    this.authenticationFailureHandler = authenticationFailureHandler;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    if (requiresAuthentication(request)) {
      try {
        Integer validateCodeType =
            ServletRequestUtils.getRequiredIntParameter(
                request, SecurityConstant.DEFAULT_PARAMETER_NAME_VALIDATE_CODE_TYPE);
        validateCodeProcessorHolder
            .findValidateCodeProcessorByType(validateCodeType)
            .validate(new ServletWebRequest(request, response));
        log.info("验证码校验通过");
      } catch (ServletRequestBindingException e) {
        authenticationFailureHandler.onAuthenticationFailure(
            request,
            response,
            new MyAuthenticationException(ResultCode.VALIDATE_CODE_TYPE_PARAM_NOT_EXIST));
        return;
      } catch (MyAuthenticationException e) {
        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
        return;
      }
    }
    chain.doFilter(request, response);
  }

  private boolean requiresAuthentication(HttpServletRequest request) {
    return this.requiresAuthenticationRequestMatcher.matches(request);
  }
}
