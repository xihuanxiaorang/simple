package top.xiaorang.simple.system.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import top.xiaorang.simple.system.entity.user.SysUser;

public interface SysUserService extends UserDetailsService {
  /**
   * 通过手机号获取系统用户
   *
   * @param phone 手机号
   * @return 系统用户
   */
  SysUser loadUserByPhone(String phone);
}
