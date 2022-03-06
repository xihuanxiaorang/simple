package top.xiaorang.simple.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {
  SUCCESS(200, "请求成功"),
  INNER_ERROR(500, "系统内部错误"),
  BAD_REQUEST(400, "请求错误"),
  UNAUTHORIZED(401, "未登录"),
  FORBIDDEN(403, "无权操作"),
  NOT_FOUND(404, "没有找到"),
  USER_CREDENTIALS_ERROR(1001, "账号或密码错误！"),
  USER_ACCOUNT_EXPIRED(1002, "账号已过期！"),
  USER_CREDENTIALS_EXPIRED(1003, "密码已过期！"),
  USER_ACCOUNT_DISABLE(1004, "账号不可用，请联系管理员！"),
  USER_ACCOUNT_LOCKED(1005, "账号被冻结，请联系管理员！"),
  USER_ACCOUNT_NOT_EXIST(1006, "账号不存在，请联系管理员！"),
  USER_NOT_LOGIN(1007, "未登录，请先登录！"),
  NO_PERMISSION(1008, "权限不足，请联系管理员！"),
  VALIDATE_CODE_PROCESSOR_NOT_EXIST(1009, "验证码处理器不存在！"),
  MOBILE_NOT_EXIST(1010, "手机号不存在！"),
  MOBILE_IS_BLANK(1011, "手机号为空！"),
  VALIDATE_CODE_CREATE_SUCCESS(1012, "验证码生成成功");

  private final Integer code;
  private final String message;
}
