package top.xiaorang.simple.system.security.extand.validatecode;

import cn.hutool.core.util.EnumUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.exception.MyAuthenticationException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidateCodeProcessorHolder {
  private final List<ValidateCodeProcessor> validateCodeProcessors;

  public ValidateCodeProcessor findValidateCodeProcessorByType(Integer type) {
    return this.findValidateCodeProcessorByType(EnumUtil.getEnumAt(ValidateCodeType.class, type));
  }

  public ValidateCodeProcessor findValidateCodeProcessorByType(ValidateCodeType validateCodeType) {
    return validateCodeProcessors.stream()
        .filter(validateCodeProcessor -> validateCodeProcessor.supports(validateCodeType))
        .findFirst()
        .orElseThrow(
            () -> new MyAuthenticationException(ResultCode.VALIDATE_CODE_PROCESSOR_NOT_EXIST));
  }
}
