package top.xiaorang.simple.system.security;

public class SecurityConstant {
  /** Ip登录请求处理URL */
  public static final String DEFAULT_IP_LOGIN_PROCESSING_URL = "/api/auth/ip-login";
  /** 验证码请求处理前缀 */
  public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/api/auth/code";
  /** 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称 */
  public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
  /** 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称 */
  public static final String DEFAULT_PARAMETER_NAME_IMAGE_CODE = "imageCode";
  /** 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称 */
  public static final String DEFAULT_PARAMETER_NAME_SMS_CODE = "smsCode";
  /** 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称 */
  public static final String DEFAULT_PARAMETER_NAME_VALIDATE_CODE_TYPE = "validateCodeType";
  /** 默认的用户名密码登录请求处理url */
  public static final String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/api/auth/form";
  /** 默认的手机验证码登录请求处理url */
  public static final String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/api/auth/mobile";
  /** 登录接口的白名单 */
  protected static final String[] LOGIN_WHITE_URL = {DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*"};

  private SecurityConstant() {}
}
