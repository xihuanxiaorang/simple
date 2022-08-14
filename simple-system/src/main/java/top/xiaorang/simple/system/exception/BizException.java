package top.xiaorang.simple.system.exception;

import lombok.Getter;
import top.xiaorang.simple.common.enums.ResultCode;

@Getter
public class BizException extends RuntimeException {
    private final Integer code;
    private final String message;

    public BizException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public BizException(String message) {
        this(ResultCode.INNER_ERROR.getCode(), message);
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
