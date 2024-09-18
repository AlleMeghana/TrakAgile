package com.trackAgile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.trackAgile.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public UserDetailsService userDetailService() {

//		authentication

//		UserDetails admin = User.withUsername("Meghana")
//				.password(encoder.encode("meghana"))
//				.roles("ADMIN").build();
//
//		UserDetails user = User.withUsername("puppy")
//				.password(encoder.encode("puppy"))
//				.roles("USER").build();
//
//		return new InMemoryUserDetailsManager(admin, user);

		return new UserInfoUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/login/save-user", "/login/authenticate", "/leave/apply/{id}", "employee/save",
						"/employee/create", "/leave/location", "/attendence/mark/{id}", "/employee/get",
						"/attendence/get-attendence", "/attendence/loc", "/attendence/update",
						"/attendence/point-update/{id}", "/attendence/get/{id}", "/attendence/get-all-locations",
						"/attendence/multipoint/{id}", "/leave/calculate/{id}", "/attendence/all-loc",
						"/site/info-save", "/site/get", "/site/upload-excel", "/login/update-password",
						"/employee/getById/{id}", "/dashboard/summary", "/dashboard/summary/{date}", "/login/all",
						"/login/{id}", "/login/username/{username}", "/attendence/start", "/attendence/mark-out",
						"/employee/getByUserName/{userName}", "/attendence/update-location/{employeeId}",
						"/dashboard/fe/{empId}", "/attendence/patroler/{id}", "/task/update/{empId}", "/api/countries",
						"/api/{country}/states", "/api/{country}/states/{state}/pkgs",
						"/api/{country}/states/{state}/pkg/{pkg}/districts",
						"/api/{country}/states/{state}/pkg/{pkg}/districts/{district}/mandals",
						"/api/{country}/states/{state}/pkg/{pkg}/districts/{district}/mandals/{mandal}/villages",
						"/task/taskInfo/{empId}")
				.permitAll().and().authorizeHttpRequests().requestMatchers("/").authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

//		return null;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();

	}

	protected void configure1(HttpSecurity http) throws Exception {

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				// other configurations
				.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}

}
