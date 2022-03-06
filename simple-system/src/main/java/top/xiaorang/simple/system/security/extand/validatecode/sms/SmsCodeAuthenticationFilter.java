package top.xiaorang.simple.system.security.extand.validatecode.sms;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.xiaorang.simple.system.security.SecurityConstant;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeAuthenticationProcessingFilter;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeProcessorHolder;

public class SmsCodeAuthenticationFilter extends ValidateCodeAuthenticationProcessingFilter {
  private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
      new AntPathRequestMatcher(SecurityConstant.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST");

  public SmsCodeAuthenticationFilter(
      ValidateCodeProcessorHolder validateCodeProcessorHolder,
      AuthenticationFailureHandler authenticationFailureHandler) {
    super(
        DEFAULT_ANT_PATH_REQUEST_MATCHER,
        validateCodeProcessorHolder,
        authenticationFailureHandler);
  }
}
