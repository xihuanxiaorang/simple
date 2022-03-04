package top.xiaorang.simple.system.security.ip;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
      new AntPathRequestMatcher("/ip-login", "POST");
  private boolean postOnly = true;

  public IpAuthenticationFilter() {
    super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
  }

  public IpAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (this.postOnly && !request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    } else {
      IpAuthenticationToken authRequest = new IpAuthenticationToken(request.getRemoteHost());
      return this.getAuthenticationManager().authenticate(authRequest);
    }
  }
}
