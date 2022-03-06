package top.xiaorang.simple.system.security.extand.validatecode;

import lombok.Data;

@Data
public class ValidateCodeProperties {
  /** 验证码长度 */
  private int length = 6;
  /** 过期时间 */
  private int expireIn = 60;
}
