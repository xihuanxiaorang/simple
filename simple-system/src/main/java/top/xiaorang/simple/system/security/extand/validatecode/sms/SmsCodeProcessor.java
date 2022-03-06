package top.xiaorang.simple.system.security.extand.validatecode.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.security.extand.validatecode.AbstractValidateCodeProcessor;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeRepository;

@Component
@RequiredArgsConstructor
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {
  private final SmsCodeGenerator smsCodeGenerator;
  private final ValidateCodeRepository validateCodeRepository;
  private final SmsCodeSender smsCodeSender;

  @Override
  public JsonResult create(ServletWebRequest request) {
    SmsCode smsCode = smsCodeGenerator.generate(request);
    validateCodeRepository.save(smsCode, ValidateCodeType.SMS);
    return smsCodeSender.send(smsCode);
  }

  @Override
  public boolean supports(ValidateCodeType validateCodeType) {
    return ValidateCodeType.SMS.equals(validateCodeType);
  }
}
