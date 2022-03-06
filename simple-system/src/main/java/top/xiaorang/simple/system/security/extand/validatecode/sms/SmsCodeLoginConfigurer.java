package top.xiaorang.simple.system.security.extand.validatecode.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.system.security.MyAuthenticationFailureHandler;
import top.xiaorang.simple.system.security.MyAuthenticationSuccessHandler;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeRepository;
import top.xiaorang.simple.system.service.user.SysUserService;

@Component
@RequiredArgsConstructor
public class SmsCodeLoginConfigurer
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  private final MyAuthenticationSuccessHandler authenticationSuccessHandler;
  private final MyAuthenticationFailureHandler authenticationFailureHandler;
  private final SysUserService sysUserService;
  private final ValidateCodeRepository validateCodeRepository;

  @Override
  public void init(HttpSecurity http) {
    http.authenticationProvider(
        postProcess(new SmsCodeAuthenticationProvider(sysUserService, validateCodeRepository)));
  }

  @Override
  public void configure(HttpSecurity http) {
    SmsCodeLoginFilter smsCodeLoginFilter = new SmsCodeLoginFilter();
    smsCodeLoginFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    smsCodeLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    smsCodeLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
    http.addFilterAfter(smsCodeLoginFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
