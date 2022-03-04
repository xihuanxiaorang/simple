package top.xiaorang.simple.system.security;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.xiaorang.simple.common.pojo.JsonResult;
import top.xiaorang.simple.common.utils.JsonResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final JwtTokenUtil jwtTokenUtil;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_OK);
    JsonResult result =
        JsonResultUtil.success(jwtTokenUtil.generateToken(authentication.getName()));
    response.getWriter().write(JSONUtil.toJsonStr(result));
  }
}
