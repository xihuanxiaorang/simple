package top.xiaorang.simple.system.security.extand.validatecode;

import top.xiaorang.simple.system.enums.ValidateCodeType;

public interface ValidateCodeRepository {
  /**
   * 保存验证码
   *
   * @param validateCode 验证码
   * @param validateCodeType 验证码类型
   */
  void save(ValidateCode validateCode, ValidateCodeType validateCodeType);
  /**
   * 获取验证码
   *
   * @param validateCodeType 验证码类型
   * @return 验证码
   */
  ValidateCode get(ValidateCodeType validateCodeType);
  /**
   * 移除验证码
   *
   * @param validateCodeType 验证码类型
   */
  void remove(ValidateCodeType validateCodeType);
}
