package in.frodo.security.authentication;

import in.frodo.dao.entity.AuthenticationToken;
import in.frodo.dao.repo.AuthenticationTokenRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class CustomAuthenticationProvider implements AuthenticationProvider {
  
  private AuthenticationTokenRepository authenticationTokenRepository;
  
  public CustomAuthenticationProvider(){
    System.out.println("AuthenticationProvider instance created");
  }
  
  public CustomAuthenticationProvider(AuthenticationTokenRepository authenticationTokenRepository){
    System.out.println("AuthenticationProvider instance created");
    this.authenticationTokenRepository = authenticationTokenRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    System.out.println("AuthenticationProvider: Providing Authentication");
    String token = "";
    try{
      token = String.class.cast(authentication.getPrincipal());
    }catch(ClassCastException e) {
      
    }
    if(!validateToken(token)){
      throw new CustomAuthenticationException("Invalid Token");
    }
    
    AuthenticationToken authenticationToken = authenticationTokenRepository.findOneByToken(token);
    
    if(authenticationToken != null){
      List<SimpleGrantedAuthority> athorities = new ArrayList<SimpleGrantedAuthority>();
      athorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      athorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
            
      authentication = new CustomAuthentication("token", token, null, null, athorities);     
      authentication.setAuthenticated(true);
      System.out.println("AuthenticationProvider: Authentication Successful");
    } else {
      throw new CustomAuthenticationException("Invalid Token");
    }
    
    /*if(token.equals("abc")){      
      
    }else{
      throw new CustomAuthenticationException("Invalid Token");
    }*/
    return authentication;
  }
  
  private boolean validateToken(String token){
    if(token == null){
      return false;
    }else return true;    
  }

  @Override
  public boolean supports(Class<?> authentication) {    
    return true;
  }

}
