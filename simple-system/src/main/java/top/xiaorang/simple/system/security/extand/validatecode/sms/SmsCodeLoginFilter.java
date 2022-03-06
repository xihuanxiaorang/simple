package top.xiaorang.simple.system.security.extand.validatecode.sms;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.xiaorang.simple.system.security.SecurityConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeLoginFilter extends AbstractAuthenticationProcessingFilter {
  private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
      new AntPathRequestMatcher(SecurityConstant.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST");

  protected SmsCodeLoginFilter() {
    super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    } else {
      String mobile = obtainMobile(request);
      mobile = (mobile != null) ? mobile : "";
      mobile = mobile.trim();
      SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
      setDetails(request, authRequest);
      return this.getAuthenticationManager().authenticate(authRequest);
    }
  }

  protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
  }

  @Nullable
  protected String obtainMobile(HttpServletRequest request) {
    return request.getParameter(SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE);
  }
}
