package top.xiaorang.simple.common.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class JsonResult implements Serializable {
  private boolean success;
  private Integer code;
  private String message;
  private Object data;
}
