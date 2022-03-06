package top.xiaorang.simple.system.security.extand.validatecode.image;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeProperties;

import java.util.Properties;

@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@ConfigurationProperties(prefix = "simple.security.image-code")
public class ImageCodeProperties extends ValidateCodeProperties {
  /** 是否有边框，默认为“yes”，可选“yes”、“no” */
  private String border = "yes";
  /** 图片宽 */
  private int width = 200;
  /** 图片高 */
  private int height = 50;

  public ImageCodeProperties() {
    super.setLength(4);
  }

  @Bean
  public DefaultKaptcha defaultKaptcha() {
    DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
    Properties properties = new Properties();
    properties.setProperty("kaptcha.border", border);
    properties.setProperty("kaptcha.image.width", String.valueOf(width));
    properties.setProperty("kaptcha.image.height", String.valueOf(height));
    properties.setProperty("kaptcha.textproducer.char.length", String.valueOf(super.getLength()));
    defaultKaptcha.setConfig(new Config(properties));
    return defaultKaptcha;
  }
}
