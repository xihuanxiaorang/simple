package top.xiaorang.simple.system.security.extand.validatecode.sms;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.exception.MyAuthenticationException;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeRepository;
import top.xiaorang.simple.system.service.user.SysUserService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
  private final SysUserService sysUserService;
  private final ValidateCodeRepository validateCodeRepository;
  protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

  public SmsCodeAuthenticationProvider(
      SysUserService sysUserService, ValidateCodeRepository validateCodeRepository) {
    this.sysUserService = sysUserService;
    this.validateCodeRepository = validateCodeRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(
        SmsCodeAuthenticationToken.class,
        authentication,
        () ->
            this.messages.getMessage(
                "SmsCodeAuthenticationProvider.onlySupports",
                "Only SmsCodeAuthenticationToken is supported"));
    SmsCodeAuthenticationToken smsCodeAuthenticationToken =
        (SmsCodeAuthenticationToken) authentication;
    String mobile = (String) smsCodeAuthenticationToken.getPrincipal();
    SmsCode smsCode = (SmsCode) validateCodeRepository.get(ValidateCodeType.SMS);
    if (ObjectUtil.isEmpty(smsCode) || !smsCode.getMobile().equals(mobile)) {
      throw new MyAuthenticationException(ResultCode.MOBILE_PARAM_NOT_MATCH);
    }
    UserDetails userDetails = sysUserService.loadUserByPhone(mobile);
    validateCodeRepository.remove(ValidateCodeType.SMS);
    SmsCodeAuthenticationToken authenticationResult =
        new SmsCodeAuthenticationToken(mobile, userDetails.getAuthorities());
    authenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());
    return authenticationResult;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
  }

  @Override
  public void setMessageSource(MessageSource messageSource) {
    this.messages = new MessageSourceAccessor(messageSource);
  }
}
