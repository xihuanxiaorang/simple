package top.xiaorang.simple.system.enums;

import top.xiaorang.simple.system.security.SecurityConstant;

public enum ValidateCodeType {
  SMS {
    @Override
    public String getParamNameOnValidate() {
      return SecurityConstant.DEFAULT_PARAMETER_NAME_SMS_CODE;
    }
  },
  IMAGE {
    @Override
    public String getParamNameOnValidate() {
      return SecurityConstant.DEFAULT_PARAMETER_NAME_IMAGE_CODE;
    }
  };

  public abstract String getParamNameOnValidate();
}
