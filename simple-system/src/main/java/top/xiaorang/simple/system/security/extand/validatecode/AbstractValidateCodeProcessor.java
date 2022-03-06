package top.xiaorang.simple.system.security.extand.validatecode;

import cn.hutool.core.text.CharSequenceUtil;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.system.enums.ValidateCodeType;
import top.xiaorang.simple.system.exception.MyAuthenticationException;

public abstract class AbstractValidateCodeProcessor<T extends ValidateCode>
    implements ValidateCodeProcessor {
  protected final ValidateCodeRepository validateCodeRepository;
  protected final ValidateCodeGenerator<T> validateCodeGenerator;
  protected final ValidateCodeType validateCodeType;

  protected AbstractValidateCodeProcessor(
      ValidateCodeRepository validateCodeRepository,
      ValidateCodeGenerator<T> validateCodeGenerator,
      ValidateCodeType validateCodeType) {
    this.validateCodeRepository = validateCodeRepository;
    this.validateCodeGenerator = validateCodeGenerator;
    this.validateCodeType = validateCodeType;
  }

  @Override
  public JsonResult create(ServletWebRequest request) {
    T validateCode = validateCodeGenerator.generate(request);
    validateCodeRepository.save(validateCode, validateCodeType);
    return send(validateCode);
  }

  protected abstract JsonResult send(T validateCode);

  @Override
  public void validate(ServletWebRequest request) {
    ValidateCode codeInSession = validateCodeRepository.get(validateCodeType);
    String codeInRequest;
    try {
      codeInRequest =
          ServletRequestUtils.getRequiredStringParameter(
              request.getRequest(), validateCodeType.getParamNameOnValidate());
    } catch (ServletRequestBindingException e) {
      throw new MyAuthenticationException(ResultCode.VALIDATE_CODE_PARAM_NOT_EXIST);
    }
    if (StringUtils.isBlank(codeInRequest)) {
      throw new MyAuthenticationException(ResultCode.VALIDATE_CODE_IS_BLANK);
    }
    if (codeInSession == null) {
      throw new MyAuthenticationException(ResultCode.VALIDATE_CODE_NOT_EXIST);
    }
    if (codeInSession.isExpired()) {
      validateCodeRepository.remove(validateCodeType);
      throw new MyAuthenticationException(ResultCode.VALIDATE_CODE_EXPIRED);
    }
    if (!CharSequenceUtil.equals(codeInSession.getCode(), codeInRequest)) {
      throw new MyAuthenticationException(ResultCode.VALIDATE_CODE_NOT_MATCH);
    }
    if (!ValidateCodeType.SMS.equals(validateCodeType)) {
      validateCodeRepository.remove(validateCodeType);
    }
  }

  @Override
  public boolean supports(ValidateCodeType validateCodeType) {
    return this.validateCodeType.equals(validateCodeType);
  }
}
