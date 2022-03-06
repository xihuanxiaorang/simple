package top.xiaorang.simple.system.security.extand.validatecode.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;

/**
 * 默认的短信验证码发送器（用于本地测试）
 *
 * @author liulei
 */
@Slf4j
@Component
public class DefaultSmsCodeSender implements SmsCodeSender {
  @Override
  public JsonResult send(SmsCode smsCode) {
    log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
    log.info("向手机" + smsCode.getMobile() + "发送短信验证码" + smsCode.getCode());
    return JsonResultUtil.success(ResultCode.VALIDATE_CODE_CREATE_SUCCESS);
  }
}
