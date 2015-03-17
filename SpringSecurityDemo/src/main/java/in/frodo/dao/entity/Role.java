package in.frodo.dao.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
@Where(clause = "is_delete = 'f'")
public class Role {
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Where(clause = "is_delete = 'f'")
  private long roleId;
  private long roleName;
  
  @JsonIgnore
  @ManyToMany(mappedBy="roles", cascade=CascadeType.ALL,  fetch=FetchType.LAZY)
  @Where(clause="marked_for_delete = 'f'")
  private Set<User> users;
  
  public long getRoleId() {
    return roleId;
  }
  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }
  public long getRoleName() {
    return roleName;
  }
  public void setRoleName(long roleName) {
    this.roleName = roleName;
  }
  
}
