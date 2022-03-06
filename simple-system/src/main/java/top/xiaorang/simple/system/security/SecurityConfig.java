package top.xiaorang.simple.system.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.xiaorang.simple.system.security.extand.ip.IpLoginConfigurer;
import top.xiaorang.simple.system.service.user.SysUserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final SysUserService sysUserService;
  private final PasswordEncoder passwordEncoder;
  private final MyAuthenticationFailureHandler authenticationFailureHandler;
  private final MyAuthenticationSuccessHandler authenticationSuccessHandler;
  private final MyLogoutSuccessHandler logoutSuccessHandler;
  private final MyAuthenticationEntryPoint authenticationEntryPoint;
  private final MyAccessDeniedHandler accessDeniedHandler;
  private final IpLoginConfigurer ipLoginConfigurer;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(sysUserService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // 开启跨域访问
        .cors()
        .and()
        // 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        .csrf()
        .disable()
        // 全局不创建session
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .apply(ipLoginConfigurer)
        .and()
        .formLogin()
        // 表单登录成功与失败处理器
        .failureHandler(authenticationFailureHandler)
        .successHandler(authenticationSuccessHandler)
        // 退出登录成功处理器
        .and()
        .logout()
        // 退出登录成功处理器
        .logoutSuccessHandler(logoutSuccessHandler)
        .and()
        .authorizeRequests()
        // 除了白名单之外的url都需要认证
        .antMatchers(SecurityConstant.LOGIN_WHITE_URL)
        .permitAll()
        .anyRequest()
        .authenticated()
        // 异常处理器
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler)
        .and()
        // 禁用页面缓存，返回的都是json
        .headers()
        .frameOptions()
        .disable()
        .cacheControl();
  }
}
