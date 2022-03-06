package top.xiaorang.simple.system.security.extand.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator<T extends ValidateCode> {
  /**
   * 生成验证码
   *
   * @param request ServletWebRequest请求
   * @return 验证码
   */
  T generate(ServletWebRequest request);
}
