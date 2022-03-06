package top.xiaorang.simple.system.security.extand.validatecode.image;

import com.google.code.kaptcha.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeGenerator;

import java.awt.image.BufferedImage;

/**
 * 图片验证码生成器
 *
 * @author liulei
 */
@Component
@RequiredArgsConstructor
public class ImageCodeGenerator implements ValidateCodeGenerator<ImageCode> {
  private final ImageCodeProperties imageCodeProperties;
  private final Producer producer;

  @Override
  public ImageCode generate(ServletWebRequest request) {
    String code = producer.createText();
    BufferedImage image = producer.createImage(code);
    return new ImageCode(image, code, imageCodeProperties.getExpireIn());
  }
}
