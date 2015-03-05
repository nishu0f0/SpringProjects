package in.frodo.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  
  public CustomAuthenticationProvider(){
    System.out.println("AuthenticationProvider instance created");
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    System.out.println("AuthenticationProvider: Providing Authentication");
    String token = "";
    try{
      token = String.class.cast(authentication.getPrincipal());
    }catch(ClassCastException e) {
      
    }
    if(token.equals("abc")){
      System.out.println("AuthenticationProvider: Authentication Successful");
      List<SimpleGrantedAuthority> athorities = new ArrayList<SimpleGrantedAuthority>();
      athorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      athorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));      
      authentication = new CustomAuthentication("token", "abc", null, null, athorities);     
      authentication.setAuthenticated(true);
    }else{
      throw new CustomAuthenticationException("Invalid Token");
    }
    return authentication;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    
    return true;
  }

}
