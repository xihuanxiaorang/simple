package top.xiaorang.simple.system.security.extand.ip;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.xiaorang.simple.system.security.SecurityConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class IpAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
      new AntPathRequestMatcher(SecurityConstant.DEFAULT_IP_LOGIN_PROCESSING_URL, "POST");
  private boolean postOnly = true;

  public IpAuthenticationFilter() {
    super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (this.postOnly && !request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    } else {
      String ip = request.getRemoteHost();
      log.info(String.format("当前登录用户ip地址为%s", ip));
      IpAuthenticationToken authRequest = new IpAuthenticationToken(ip);
      return this.getAuthenticationManager().authenticate(authRequest);
    }
  }
}
