package in.frodo.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public CustomAuthenticationFilter(String defaultFilterProcessesUrl) {
    super(defaultFilterProcessesUrl);
    super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
    setAuthenticationManager(new ProviderManager(getAuthenticationProviders()));
    setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    System.out.println("AuthenticationFilter: Attmpting Authentication");
    String token = request.getHeader("token");
    CustomAuthentication authentication = new CustomAuthentication("token", token, null, null, null);
    return authentication;
  }
  
  private List<AuthenticationProvider> getAuthenticationProviders() {
    List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
    authenticationProviders.add(new CustomAuthenticationProvider());
    return authenticationProviders;
  }
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException{
    super.doFilter(request , response, chain);
  }
  
  

}
