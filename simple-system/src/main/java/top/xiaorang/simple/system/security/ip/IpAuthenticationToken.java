package top.xiaorang.simple.system.security.ip;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class IpAuthenticationToken extends AbstractAuthenticationToken {
  private final String ip;

  public IpAuthenticationToken(String ip) {
    super(null);
    this.ip = ip;
    this.setAuthenticated(false);
  }

  public IpAuthenticationToken(String ip, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.ip = ip;
    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return ip;
  }
}
