package top.xiaorang.simple.system.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import top.xiaorang.simple.common.enums.ResultCode;

import java.io.Serializable;

/**
 * @author liulei
 */
@Getter
@Setter
public class MyAuthenticationException extends AuthenticationException implements Serializable {
  private Integer code;
  private Object data;

  public MyAuthenticationException(ResultCode resultCode) {
    this(resultCode.getMessage(), resultCode.getCode(), null);
  }

  public MyAuthenticationException(ResultCode resultCode, Object data) {
    this(resultCode.getMessage(), resultCode.getCode(), data);
  }

  public MyAuthenticationException(String message, Integer code) {
    this(message, code, null);
  }

  public MyAuthenticationException(String message, Integer code, Object data) {
    super(message);
    this.code = code;
    this.data = data;
  }
}
