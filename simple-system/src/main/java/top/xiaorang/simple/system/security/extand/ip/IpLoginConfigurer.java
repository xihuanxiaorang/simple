package top.xiaorang.simple.system.security.extand.ip;

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
  public void init(HttpSecurity http) {
    http.authenticationProvider(postProcess(new IpAuthenticationProvider()));
  }

  @Override
  public void configure(HttpSecurity http) {
    IpAuthenticationFilter ipAuthenticationFilter = new IpAuthenticationFilter();
    ipAuthenticationFilter.setAuthenticationManager(
        http.getSharedObject(AuthenticationManager.class));
    ipAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    ipAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
    http.addFilterBefore(ipAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
