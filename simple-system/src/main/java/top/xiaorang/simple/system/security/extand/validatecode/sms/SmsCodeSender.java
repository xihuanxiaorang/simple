package top.xiaorang.simple.system.security.extand.validatecode.sms;

import top.xiaorang.simple.common.pojo.JsonResult;

/**
 * 短信验证码发送接口
 *
 * @author liulei
 */
public interface SmsCodeSender {
  /**
   * 发送短信验证码
   *
   * @param smsCode 手机号验证码
   * @return 通用结果封装对象
   */
  JsonResult send(SmsCode smsCode);
}
