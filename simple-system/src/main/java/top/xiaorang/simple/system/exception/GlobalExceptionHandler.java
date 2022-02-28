package top.xiaorang.simple.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   * 拦截业务异常
   *
   * @param e 业务异常
   * @return 通用结果封装对象
   */
  @ExceptionHandler(BizException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult bizExceptionHandler(BizException e) {
    return JsonResultUtil.error(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult exceptionHandler(Exception e) {
    return JsonResultUtil.error();
  }
}
