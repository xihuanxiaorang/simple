package top.xiaorang.simple.system.security.extand.validatecode.sms;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCode;

import java.time.LocalDateTime;

/**
 * 手机验证码
 *
 * @author liulei
 */
@Data
@NoArgsConstructor
public class SmsCode extends ValidateCode {
  private String mobile;

  /**
   * SmsCode构造器
   *
   * @param mobile 手机号
   * @param code 验证码
   * @param expireIn 多少秒过期
   */
  public SmsCode(String mobile, String code, int expireIn) {
    super(code, expireIn);
    this.mobile = mobile;
  }

  /**
   * SmsCode构造器
   *
   * @param mobile 手机号
   * @param code 验证码
   * @param expireTime 过期时间
   */
  public SmsCode(String mobile, String code, LocalDateTime expireTime) {
    super(code, expireTime);
    this.mobile = mobile;
  }
}
