package top.xiaorang.simple.system.security.extand.ip;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IpAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
  protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
  private static final Map<String, SimpleGrantedAuthority> ipAuthorityMap =
      new ConcurrentHashMap<>();

  static {
    ipAuthorityMap.put("127.0.0.1", new SimpleGrantedAuthority("ADMIN"));
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(
        IpAuthenticationToken.class,
        authentication,
        () ->
            this.messages.getMessage(
                "IpAuthenticationProvider.onlySupports",
                "Only IpAuthenticationToken is supported"));
    String ip = (String) authentication.getPrincipal();
    if (ipAuthorityMap.containsKey(ip)) {
      return new IpAuthenticationToken(ip, Collections.singletonList(ipAuthorityMap.get(ip)));
    }
    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return IpAuthenticationToken.class.isAssignableFrom(authentication);
  }

  @Override
  public void setMessageSource(MessageSource messageSource) {
    this.messages = new MessageSourceAccessor(messageSource);
  }
}
