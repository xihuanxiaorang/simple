package top.xiaorang.simple.system.security.extand.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator<T> {
  /**
   * 生成验证码
   *
   * @param request
   * @return 验证码
   */
  T generate(ServletWebRequest request);
}
