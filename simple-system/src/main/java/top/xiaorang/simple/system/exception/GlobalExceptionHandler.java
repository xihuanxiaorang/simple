package top.xiaorang.simple.system.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;

@Slf4j
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

  /**
   * 拦截自定义认证异常
   *
   * @param e 自定义认证异常
   * @return 通用结果封装对象
   */
  @ExceptionHandler(MyAuthenticationException.class)
  public JsonResult validateCodeError(MyAuthenticationException e) {
    log.error(">>> 业务异常，请求号为：{}，具体信息为：{}", e.getCode(), e.getMessage());
    return JsonResultUtil.error(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult exceptionHandler(Exception e) {
    return JsonResultUtil.error();
  }
}
