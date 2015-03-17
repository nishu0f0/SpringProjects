package in.frodo.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "authentication_token")
@Where(clause = "is_delete = 'f'")
public class AuthenticationToken extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Where(clause = "is_delete = 'f'")
  private long tokenId;
  private long userId;
  private String username;  
  private String token;
  private long tokenExpiry;
  
  public AuthenticationToken(){
    
  }
  
  public AuthenticationToken(long userId, String username, String token) {
    long timestamp = System.currentTimeMillis();
    this.userId = userId;
    this.username = username;
    this.token = token;
    this.tokenExpiry = timestamp + 86400000;
  }
  
  
  public long getTokenId() {
    return tokenId;
  }
  public void setTokenId(long tokenId) {
    this.tokenId = tokenId;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public long getUserId() {
    return userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public long getTokenExpiry() {
    return tokenExpiry;
  }
  public void setTokenExpiry(long tokenExpiry) {
    this.tokenExpiry = tokenExpiry;
  }
  
}
