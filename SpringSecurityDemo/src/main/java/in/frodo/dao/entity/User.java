package in.frodo.dao.entity;

import in.frodo.util.HashUtil;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
@Where(clause = "is_delete = 'f'")
public class User extends BaseEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Where(clause = "is_delete = 'f'")
  private long userId;
  private String username;
  private String name;
  private String email;
  private String passwordHash;
  private String passwordSalt;
  
  public User() {
    
  }
  
  public User(String username, String password) {
    this.username = username;
    this.email = "";
    this.roles = new HashSet<Role>();
    this.name = username;
    this.generatePasswordSalt();
    this.generatePasswordHash(password);    
  }
  
  
  @JsonIgnore
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Where(clause = "marked_for_delete = 'f'")
  @JoinTable(name = "user_roles")
  private Set<Role> roles;
  
  
  public long getUserId() {
    return userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPasswordHash() {
    return passwordHash;
  }
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }
  public String getPasswordSalt() {
    return passwordSalt;
  }
  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }
  public Set<Role> getRoles() {
    return roles;
  }
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
  
  public void generatePasswordSalt() {
    this.passwordSalt = HashUtil.generateRandomString(20);
  }
  
  public static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(15);
    
  public void generatePasswordHash(String password) {
    if(this.passwordSalt == null){
      generatePasswordSalt();
    }
    
    if(password != null) {
      String saltedPassword = password + this.passwordSalt;
      
      String hashedPassword = ENCODER.encode(saltedPassword);
      this.passwordHash = hashedPassword;
    }    
  }
  
  public boolean matchPassword(String password) {
    if(this.passwordHash == null) {
      this.passwordHash = "";
    }
    //Needs to be fixed
    if(this.passwordSalt == null) {
      this.passwordSalt = "random"; 
    }
    String saltedPassword = password + this.passwordSalt; 
    return ENCODER.matches(saltedPassword, this.passwordHash);
  }  
  
}
