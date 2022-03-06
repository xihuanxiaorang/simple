package top.xiaorang.simple.system.security.extand.validatecode.image;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.security.extand.validatecode.AbstractValidateCodeProcessor;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeRepository;

import javax.imageio.ImageIO;
import java.io.IOException;

@Slf4j
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
  public ImageCodeProcessor(
      ValidateCodeRepository validateCodeRepository, ImageCodeGenerator imageCodeGenerator) {
    super(validateCodeRepository, imageCodeGenerator, ValidateCodeType.IMAGE);
  }

  @Override
  protected JsonResult send(ImageCode imageCode) {
    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
    try {
      ImageIO.write(imageCode.getImage(), "png", os);
    } catch (IOException e) {
      return JsonResultUtil.error(e.getMessage());
    }
    log.info("生成的图形验证码：" + imageCode.getCode());
    return JsonResultUtil.success("data:image/png;base64," + Base64.encode(os.toByteArray()));
  }
}
