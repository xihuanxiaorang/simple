package top.xiaorang.simple.system.security.ip;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.system.security.MyAuthenticationFailureHandler;
import top.xiaorang.simple.system.security.MyAuthenticationSuccessHandler;

@Component
@RequiredArgsConstructor
public class IpLoginConfigurer
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  private final MyAuthenticationSuccessHandler authenticationSuccessHandler;
  private final MyAuthenticationFailureHandler authenticationFailureHandler;

  @Override
  public void configure(HttpSecurity http) {
    IpAuthenticationFilter ipAuthenticationFilter =
        new IpAuthenticationFilter(http.getSharedObject(AuthenticationManager.class));
    ipAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    ipAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
    http.authenticationProvider(new IpAuthenticationProvider())
        .addFilterBefore(ipAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
