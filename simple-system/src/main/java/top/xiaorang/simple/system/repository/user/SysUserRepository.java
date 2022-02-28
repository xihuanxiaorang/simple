package top.xiaorang.simple.system.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xiaorang.simple.system.entity.user.SysUser;

import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, String> {
  /**
   * 通过用户名查找系统用户
   *
   * @param username 用户名
   * @return 系统用户
   */
  Optional<SysUser> findByUsername(String username);
}
