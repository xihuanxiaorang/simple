package top.xiaorang.simple.system.entity.user;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.xiaorang.simple.common.enums.Gender;
import top.xiaorang.simple.system.entity.BaseEntity;
import top.xiaorang.simple.system.entity.role.SysRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NamedEntityGraph(
    name = "SysUser.Graph",
    attributeNodes = {@NamedAttributeNode("sysRoles")})
public class SysUser extends BaseEntity implements UserDetails, Serializable {
  @Column(unique = true)
  private String username;

  private String nickname;

  private String password;

  private Gender gender;

  private Boolean locked;

  private Boolean enabled;

  private String phone;

  private String lastLoginIp;

  private Date lastLoginTime;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "sys_user_role",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  private List<SysRole> sysRoles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (CollUtil.isNotEmpty(sysRoles)) {
      return sysRoles.stream()
          .map(SysRole::getTitle)
          .map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
