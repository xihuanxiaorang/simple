package top.xiaorang.simple.system.security;

import cn.hutool.json.JSONUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.common.enums.ResultCode;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;
import top.xiaorang.simple.system.exception.MyAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
      throws IOException {
    JsonResult result;
    if (e instanceof AccountExpiredException) {
      result = JsonResultUtil.error(ResultCode.USER_ACCOUNT_EXPIRED);
    } else if (e instanceof BadCredentialsException) {
      result = JsonResultUtil.error(ResultCode.USER_CREDENTIALS_ERROR);
    } else if (e instanceof CredentialsExpiredException) {
      result = JsonResultUtil.error(ResultCode.USER_CREDENTIALS_EXPIRED);
    } else if (e instanceof DisabledException) {
      result = JsonResultUtil.error(ResultCode.USER_ACCOUNT_DISABLE);
    } else if (e instanceof LockedException) {
      result = JsonResultUtil.error(ResultCode.USER_ACCOUNT_LOCKED);
    } else if (e instanceof InternalAuthenticationServiceException
        || e instanceof UsernameNotFoundException) {
      result = JsonResultUtil.error(ResultCode.USER_ACCOUNT_NOT_EXIST);
    } else if (e instanceof MyAuthenticationException) {
      MyAuthenticationException myAuthenticationException = (MyAuthenticationException) e;
      result =
          JsonResultUtil.error(
              myAuthenticationException.getCode(),
              myAuthenticationException.getMessage(),
              myAuthenticationException.getData());
    } else {
      result = JsonResultUtil.error();
    }
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(JSONUtil.toJsonStr(result));
  }
}
