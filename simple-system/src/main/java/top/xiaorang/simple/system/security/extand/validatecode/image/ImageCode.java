package top.xiaorang.simple.system.security.extand.validatecode.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 *
 * @author liulei
 */
@Data
@NoArgsConstructor
public class ImageCode extends ValidateCode {
  private BufferedImage image;

  /**
   * ImageCode构造器
   *
   * @param image 图片
   * @param code 验证码
   * @param expireIn 多少秒过期
   */
  public ImageCode(BufferedImage image, String code, int expireIn) {
    super(code, expireIn);
    this.image = image;
  }

  /**
   * ImageCode构造器
   *
   * @param image 图片
   * @param code 验证码
   * @param expireTime 过期时间
   */
  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    super(code, expireTime);
    this.image = image;
  }
}
