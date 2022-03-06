package top.xiaorang.simple.system.security.extand.validatecode.image;

import cn.hutool.core.codec.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.security.extand.validatecode.AbstractValidateCodeProcessor;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeRepository;

import javax.imageio.ImageIO;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
  private final ImageCodeGenerator imageCodeGenerator;
  private final ValidateCodeRepository validateCodeRepository;

  @Override
  public JsonResult create(ServletWebRequest request) {
    ImageCode imageCode = imageCodeGenerator.generate(request);
    validateCodeRepository.save(imageCode, ValidateCodeType.IMAGE);
    return send(imageCode);
  }

  private JsonResult send(ImageCode imageCode) {
    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
    try {
      ImageIO.write(imageCode.getImage(), "png", os);
    } catch (IOException e) {
      return JsonResultUtil.error(e.getMessage());
    }
    log.warn("生成的图形验证码：" + imageCode.getCode());
    return JsonResultUtil.success("data:image/png;base64," + Base64.encode(os.toByteArray()));
  }

  @Override
  public boolean supports(ValidateCodeType validateCodeType) {
    return ValidateCodeType.IMAGE.equals(validateCodeType);
  }
}
