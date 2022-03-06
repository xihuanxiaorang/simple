package top.xiaorang.simple.system.security.extand.validatecode;

import org.springframework.stereotype.Component;
import top.xiaorang.simple.system.enums.ValidateCodeType;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {
  private static final ConcurrentHashMap<ValidateCodeType, ValidateCode> validateCodeMap =
      new ConcurrentHashMap<>();

  @Override
  public void save(ValidateCode validateCode, ValidateCodeType validateCodeType) {
    validateCodeMap.putIfAbsent(validateCodeType, validateCode);
  }

  @Override
  public ValidateCode get(ValidateCodeType validateCodeType) {
    return validateCodeMap.get(validateCodeType);
  }

  @Override
  public void remove(ValidateCodeType validateCodeType) {
    validateCodeMap.remove(validateCodeType);
  }
}
