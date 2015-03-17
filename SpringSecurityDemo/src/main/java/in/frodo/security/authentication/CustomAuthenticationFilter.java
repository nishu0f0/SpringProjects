package in.frodo.security.authentication;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
      
  public CustomAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
    super(defaultFilterProcessesUrl);
    super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
    setAuthenticationManager(authenticationManager);
    setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
    setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
  } 

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    System.out.println("AuthenticationFilter: Attmpting Authentication");
    String token = request.getHeader("token");
    CustomAuthentication authentication = new CustomAuthentication("token", token, null, null, null);
    Authentication successfulAuthentication = getAuthenticationManager().authenticate(authentication);
    System.out.println("AuthenticationFilter: Finised Attmpting Authentication");
    return successfulAuthentication;
  }
    
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException{
    System.out.println("########doFilter method before auth");
    super.doFilter(request , response, chain);
    System.out.println("doFilter method after auth");
    
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    boolean isAuthenticated;
    if(authentication == null) {
      System.out.println("authentication is null");
      System.out.println("doFilterAuth Check: failed");
      isAuthenticated = false;
    } else if(!authentication.isAuthenticated()) {
      System.out.println("authentication is not null");
      System.out.println(new JSONObject(authentication));
      System.out.println("doFilterAuth Check: failed");
      isAuthenticated = false;
    } else {
      System.out.println("authentication is not null");
      System.out.println("doFilterAuth Check: successful");
      isAuthenticated = true;
    }
    
    //System.out.println(new JSONObject(authentication));
    if(isAuthenticated) {
      chain.doFilter(request, response);
    } 
    
  }
}
