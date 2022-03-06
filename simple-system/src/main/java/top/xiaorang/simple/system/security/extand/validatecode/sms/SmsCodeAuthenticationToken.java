package top.xiaorang.simple.system.security.extand.validatecode.sms;

import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
  private final String mobile;

  public SmsCodeAuthenticationToken(String mobile) {
    super(null);
    this.mobile = mobile;
    this.setAuthenticated(false);
  }

  public SmsCodeAuthenticationToken(
      String mobile, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.mobile = mobile;
    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return mobile;
  }
}
