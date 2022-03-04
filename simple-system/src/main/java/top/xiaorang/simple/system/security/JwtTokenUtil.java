package top.xiaorang.simple.system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** @author liulei */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil {
  private static final String CLAIM_KEY_USERNAME = "sub";
  private static final String CLAIM_KEY_CREATED = "created";
  private long expire = 7200L;
  private String secret = "90155c2b100fa24c4e3b6424f420b3bdec25";
  private String header = "Authorization";
  private String head = "Barear";

  /**
   * 通过用户信息生成token令牌
   *
   * @param username 用户信息
   * @return token令牌
   */
  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>(2);
    claims.put(CLAIM_KEY_USERNAME, username);
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }

  /**
   * 通过token获取登录用户名
   *
   * @param token token令牌
   * @return 用户名
   */
  public String getUsernameFromToken(String token) {
    return getClaimByToken(token).getSubject();
  }

  /**
   * 判断token是否有效
   *
   * @param token token令牌
   * @param userDetails 用户信息
   * @return true/false
   */
  public boolean validateToken(String token, UserDetails userDetails) {
    return userDetails.getUsername().equals(getUsernameFromToken(token)) && !isTokenExpired(token);
  }

  /**
   * 判断token是否可以被刷新
   *
   * @param token
   * @return
   */
  public boolean canRefresh(String token) {
    return !isTokenExpired(token);
  }

  /**
   * 刷新token
   *
   * @param token 旧的token
   * @return 新的token
   */
  public String refreshToken(String token) {
    Claims claims = getClaimByToken(token);
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }

  /**
   * 判断token是否过期
   *
   * @param token token令牌
   * @return true/false
   */
  private boolean isTokenExpired(String token) {
    return getExpireDateFromToken(token).before(new Date());
  }

  /**
   * 获取token中的过期时间
   *
   * @param token token令牌
   * @return 过期时间
   */
  private Date getExpireDateFromToken(String token) {
    return getClaimByToken(token).getExpiration();
  }

  /**
   * 获取token中的载荷
   *
   * @param token token令牌
   * @return claims信息
   */
  private Claims getClaimByToken(String token) {
    return Optional.ofNullable(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody())
        .orElseThrow(() -> new JwtException("token 解析异常"));
  }

  /**
   * 根据载荷生成token令牌
   *
   * @param claims 载荷
   * @return token令牌
   */
  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(generateExpirationDate())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  /**
   * 生成token失效时间
   *
   * @return 失效时间
   */
  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expire * 1000);
  }
}
