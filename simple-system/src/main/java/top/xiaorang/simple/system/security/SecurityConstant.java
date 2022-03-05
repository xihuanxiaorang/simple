package top.xiaorang.simple.system.security;

public class SecurityConstant {
  /** Ip登录请求处理URL */
  public static final String IP_LOGIN_PROCESSING_URL = "/api/auth/ip-login";
  /** 默认的用户名密码登录请求处理url */
  public static final String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/api/auth/form";
  /** 登录接口的白名单 */
  protected static final String[] LOGIN_WHITE_URL = {};

  private SecurityConstant() {}
}
