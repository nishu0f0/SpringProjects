package in.frodo.config;

import java.util.ArrayList;
import java.util.List;

import in.frodo.dao.entity.AuthenticationToken;
import in.frodo.dao.entity.User;
import in.frodo.dao.repo.AuthenticationTokenRepository;
import in.frodo.dao.repo.UserRepository;
import in.frodo.security.authentication.CustomAuthenticationFilter;
import in.frodo.security.authentication.CustomAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  AuthenticationTokenRepository authenticationTokenRepository;
  
  @Autowired
  UserRepository userRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterBefore(getTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().antMatchers("/**").hasRole("USER").anyRequest().authenticated();//.and()
    http.authorizeRequests().antMatchers("/login").permitAll();
    //anonymous().disable();
        //.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());
    
    addDataIntoDB();
    
  }

  private AbstractAuthenticationProcessingFilter getTokenAuthenticationFilter() {
    CustomAuthenticationFilter filter = new CustomAuthenticationFilter("/**", getAuthenticationManager());
    return filter;
  }
    
  private AuthenticationManager getAuthenticationManager(){
    return new ProviderManager(getAuthenticationProviders());
  }
  
  private List<AuthenticationProvider> getAuthenticationProviders() {
    List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
    authenticationProviders.add(new CustomAuthenticationProvider(authenticationTokenRepository));
    return authenticationProviders;
  }
  
  private void addDataIntoDB(){    
    authenticationTokenRepository.save(new AuthenticationToken(1, "frodo", "abc"));    
    userRepository.save(new User("frodo", "frodo0f0"));
    userRepository.save(new User("nishanth", "frodo0f0"));
  }
  

}
