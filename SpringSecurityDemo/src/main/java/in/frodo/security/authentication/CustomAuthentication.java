package in.frodo.security.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthentication implements Authentication {

  private String name;
  private Object principal;
  private Object credentials;
  private Object details;
  private Collection<? extends GrantedAuthority> athorities;
  private boolean authenticated;

  public CustomAuthentication() {
    this.name = null;
    this.principal = null;
    this.credentials = null;
    this.details = null;
    this.athorities = new ArrayList<SimpleGrantedAuthority>();
    this.authenticated = false;
    //System.out.println("Authentication: " + name + " " + principal + " " + credentials + " " + details + " " + authenticated);
  }

  public CustomAuthentication(String name, Object principal, Object credentials, Object details,
      Collection<? extends GrantedAuthority> athorities) {
    this.name = name;
    this.principal = principal;
    this.credentials = credentials;
    this.details = details;
    this.athorities = athorities;
    this.authenticated = false;
    //System.out.println("Authentication: " + name + " " + principal + " " + credentials + " " + details + " " + authenticated);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return this.athorities;
  }

  @Override
  public Object getCredentials() {
    // TODO Auto-generated method stub
    return this.credentials;
  }

  @Override
  public Object getDetails() {
    // TODO Auto-generated method stub
    return this.details;
  }

  @Override
  public Object getPrincipal() {
    // TODO Auto-generated method stub
    return this.principal;
  }

  @Override
  public boolean isAuthenticated() {
    // TODO Auto-generated method stub
    return this.authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    this.authenticated = isAuthenticated;
  }

}
