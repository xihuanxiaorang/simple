package top.xiaorang.simple.system.security.extand.validatecode;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.system.security.MyAuthenticationFailureHandler;
import top.xiaorang.simple.system.security.extand.validatecode.image.ImageCodeAuthenticationFilter;
import top.xiaorang.simple.system.security.extand.validatecode.sms.SmsCodeAuthenticationFilter;

@Component
@RequiredArgsConstructor
public class ValidateCodeConfigurer
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  private final ValidateCodeProcessorHolder validateCodeProcessorHolder;
  private final MyAuthenticationFailureHandler authenticationFailureHandler;

  @Override
  public void configure(HttpSecurity http) {
    ImageCodeAuthenticationFilter imageCodeAuthenticationFilter =
        new ImageCodeAuthenticationFilter(
            validateCodeProcessorHolder, authenticationFailureHandler);
    SmsCodeAuthenticationFilter smsCodeAuthenticationFilter =
        new SmsCodeAuthenticationFilter(validateCodeProcessorHolder, authenticationFailureHandler);
    http.addFilterBefore(imageCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
