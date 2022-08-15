package top.xiaorang.simple.system.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import top.xiaorang.simple.system.entity.user.SysUser;

import java.util.Optional;

public interface SysUserService extends UserDetailsService {
    /**
     * 通过手机号获取系统用户
     *
     * @param phone 手机号
     * @return 系统用户
     */
    SysUser loadUserByPhone(String phone);

    /**
     * 通过用户名获取系统用户
     *
     * @param username 用户名
     * @return 系统用户
     */
    Optional<SysUser> getUserByUsername(String username);

    /**
     * 保存系统用户信息
     *
     * @param sysUser 系统用户
     */
    void save(SysUser sysUser);
}
