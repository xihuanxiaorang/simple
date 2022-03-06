package top.xiaorang.simple.common.utils;

import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.common.pojo.JsonResult;

public class JsonResultUtil {
  private JsonResultUtil() {}

  /**
   * 返回通用的成功JsonResult对象
   *
   * @return JsonResult对象
   */
  public static JsonResult success() {
    return success(null);
  }

  /**
   * 返回通用的成功JsonResult对象
   *
   * @return JsonResult对象
   */
  public static JsonResult success(Object data) {
    return JsonResult.builder()
        .success(true)
        .code(ResultCode.SUCCESS.getCode())
        .message(ResultCode.SUCCESS.getMessage())
        .data(data)
        .build();
  }

  /**
   * 返回通用的成功JsonResult对象
   *
   * @return JsonResult对象
   */
  public static JsonResult success(ResultCode resultCode) {
    return JsonResult.builder()
        .success(true)
        .code(resultCode.getCode())
        .message(resultCode.getMessage())
        .build();
  }

  /**
   * 返回通用的错误/异常JsonResult对象
   *
   * @return JsonResult对象
   */
  public static JsonResult error() {
    return error(ResultCode.INNER_ERROR.getCode(), ResultCode.INNER_ERROR.getMessage(), null);
  }

  /**
   * 根据消息返回对应的JsonResult对象
   *
   * @param message 消息
   * @return JsonResult对象
   */
  public static JsonResult error(String message) {
    return error(ResultCode.INNER_ERROR.getCode(), message, null);
  }

  /**
   * 根据响应码返回对应的JsonResult对象
   *
   * @param resultCode 响应码枚举
   * @return JsonResult对象
   */
  public static JsonResult error(ResultCode resultCode) {
    return error(resultCode.getCode(), resultCode.getMessage(), null);
  }

  /**
   * 根据响应码和消息返回JsonResult对象
   *
   * @param code 响应码
   * @param message 消息
   * @return JsonResult对象
   */
  public static JsonResult error(Integer code, String message) {
    return error(code, message, null);
  }

  /**
   * 根据响应码和消息和数据返回JsonResult对象
   *
   * @param code 响应码
   * @param message 消息
   * @param data 数据
   * @return JsonResult对象
   */
  public static JsonResult error(Integer code, String message, Object data) {
    return JsonResult.builder().success(false).code(code).message(message).data(data).build();
  }
}
