package top.xiaorang.simple.system.security.extand.validatecode.sms;

import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.system.exception.MyAuthenticationException;
import top.xiaorang.simple.system.security.SecurityConstant;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeGenerator;

/**
 * 短信验证码生成器
 *
 * @author liulei
 */
@Component
@RequiredArgsConstructor
public class SmsCodeGenerator implements ValidateCodeGenerator<SmsCode> {
  private final SmsCodeProperties smsCodeProperties;

  @Override
  public SmsCode generate(ServletWebRequest request) {
    String mobile;
    try {
      mobile =
          ServletRequestUtils.getRequiredStringParameter(
              request.getRequest(), SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE);
    } catch (ServletRequestBindingException e) {
      throw new MyAuthenticationException(ResultCode.MOBILE_NOT_EXIST);
    }
    if (StringUtils.isBlank(mobile)) {
      throw new MyAuthenticationException(ResultCode.MOBILE_IS_BLANK);
    }
    String code = RandomUtil.randomNumbers(smsCodeProperties.getLength());
    return new SmsCode(mobile, code, smsCodeProperties.getExpireIn());
  }
}
