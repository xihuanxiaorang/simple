package top.xiaorang.simple.system.security;

public interface SecurityConfigConstant {
  /** 默认的用户名密码登录请求处理url */
  String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/api/auth/form";

  /** 登录接口的白名单 */
  String[] LOGIN_WHITE_URL = {
    DEFAULT_SIGN_IN_PROCESSING_URL_FORM,
  };
}
