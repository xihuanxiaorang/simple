package top.xiaorang.simple.system.security.extand.validatecode;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ValidateCode implements Serializable {
  private String code;
  private LocalDateTime expireTime;

  /**
   * ValidateCode构造器
   *
   * @param code 验证码
   * @param expireTime 过期时间
   */
  public ValidateCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  /**
   * ValidateCode构造器
   *
   * @param code 验证码
   * @param expireIn 多少秒过期
   */
  public ValidateCode(String code, int expireIn) {
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  /**
   * 判断当前验证码是否过期
   *
   * @return true or false
   */
  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
