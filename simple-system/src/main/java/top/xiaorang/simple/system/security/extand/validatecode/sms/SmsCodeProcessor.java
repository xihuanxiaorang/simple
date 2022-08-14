package top.xiaorang.simple.system.security.extand.validatecode.sms;

import org.springframework.stereotype.Component;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.security.extand.validatecode.AbstractValidateCodeProcessor;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeRepository;

@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {
    private final SmsCodeSender smsCodeSender;

    public SmsCodeProcessor(
            SmsCodeGenerator smsCodeGenerator,
            ValidateCodeRepository validateCodeRepository,
            SmsCodeSender smsCodeSender) {
        super(validateCodeRepository, smsCodeGenerator, ValidateCodeType.SMS);
        this.smsCodeSender = smsCodeSender;
    }

    @Override
    protected JsonResult<?> send(SmsCode smsCode) {
        return smsCodeSender.send(smsCode);
    }
}
