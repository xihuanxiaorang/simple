package top.xiaorang.simple.system.repository.user;

import org.springframework.data.jpa.repository.EntityGraph;
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
  @EntityGraph(value = "SysUser.Graph", type = EntityGraph.EntityGraphType.FETCH)
  Optional<SysUser> findByUsername(String username);

  /**
   * 通过手机号查找系统用户
   *
   * @param phone 手机号
   * @return 系统用户
   */
  @EntityGraph(value = "SysUser.Graph", type = EntityGraph.EntityGraphType.FETCH)
  Optional<SysUser> findByPhone(String phone);
}
