package top.xiaorang.simple.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.system.security.SecurityConstant;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeProcessorHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX)
@RequiredArgsConstructor
public class ValidateCodeController {
  private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

  /**
   * @param validateCodeType 验证码类型
   * @param request 请求
   * @param response 响应
   */
  @GetMapping("/{validateCodeType}")
  public JsonResult createValidateCode(
      @PathVariable Integer validateCodeType,
      HttpServletRequest request,
      HttpServletResponse response) {
    return validateCodeProcessorHolder
        .findValidateCodeProcessorByType(validateCodeType)
        .create(new ServletWebRequest(request, response));
  }
}
