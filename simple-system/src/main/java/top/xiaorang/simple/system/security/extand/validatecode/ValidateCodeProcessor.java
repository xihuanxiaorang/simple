package top.xiaorang.simple.system.security.extand.validatecode;

import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.system.enums.ValidateCodeType;

public interface ValidateCodeProcessor {
  /**
   * 创建验证码
   *
   * @param request 请求
   * @return JsonResult对象
   */
  JsonResult create(ServletWebRequest request);

  boolean supports(ValidateCodeType validateCodeType);
}
