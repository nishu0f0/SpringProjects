package in.frodo.config;

import java.util.ArrayList;
import java.util.List;

import in.frodo.authentication.CustomAuthenticationFilter;
import in.frodo.authentication.CustomAuthenticationProvider;
import in.frodo.authentication.RestAuthenticationEntryPoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().antMatchers("/").hasRole("USER").anyRequest().authenticated().and()
        .anonymous().disable().exceptionHandling()
        .authenticationEntryPoint(unauthorizedEntryPoint());
  }

  private String[] actuatorEndpoints() {
    return new String[] { "/" };
  }

  private AuthenticationEntryPoint unauthorizedEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }

  public AbstractAuthenticationProcessingFilter getAuthenticationFilter() {
    CustomAuthenticationFilter filter = new CustomAuthenticationFilter("/*");
    return filter;
  }
  
  //@Bean
  public AuthenticationManager getAuthenticationManager(){
    return new ProviderManager(getAuthenticationProviders());
  }
  
  private List<AuthenticationProvider> getAuthenticationProviders() {
    List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
    authenticationProviders.add(new CustomAuthenticationProvider());
    return authenticationProviders;
  }

}
