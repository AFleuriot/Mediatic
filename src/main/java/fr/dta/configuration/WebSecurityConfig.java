package fr.dta.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import fr.dta.authentication.service.AuthenticationService;
import fr.dta.authentication.util.RestAuthenticationFailureHandler;
import fr.dta.authentication.util.RestAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

	
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	@Autowired
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println(new BCryptPasswordEncoder(10).encode("password"));
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		.and()
			.authorizeRequests()
			//.antMatchers("/app/**").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/app/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers(HttpMethod.POST, "/adherent", "/media").hasAuthority("ADMIN")//.access("hasRole('ADMIN')")
			.antMatchers(HttpMethod.PUT, "/adherent", "/media").hasAuthority("USER")//.access("hasRole('ADMIN')")
			.antMatchers("/emprunt").hasAuthority("USER")//.access("hasRole('USER')")
			.antMatchers(HttpMethod.GET, "/adherent", "/media").hasAuthority("USER")//.access("hasRole('USER')")
			
			//.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/app/index.html#!/accueil")
			.loginProcessingUrl("/login")
			.successHandler(restAuthenticationSuccessHandler)
			.failureHandler(restAuthenticationFailureHandler)
			//.defaultSuccessUrl("/public/app/index.html#!/rechercheMedia")
		.and()
			.logout()
			.logoutUrl("/logout")
		.and()		
			.httpBasic()
		.and()
			.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	/*@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	//auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
		auth  
		.inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
		
	}*/
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
		
	}
}
