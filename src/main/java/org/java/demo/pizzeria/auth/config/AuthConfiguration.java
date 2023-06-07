package org.java.demo.pizzeria.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.csrf(c-> c.disable())
				.authorizeHttpRequests( a -> a
				.requestMatchers("/*/create/**").hasAuthority("ADMIN")
				.requestMatchers("/*/edit/**").hasAuthority("ADMIN")
				.requestMatchers("/*/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/special-offers/**").hasAuthority("ADMIN")
				.requestMatchers("/ingredients/**").hasAuthority("ADMIN")
				.requestMatchers("/pizzas/**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/**").permitAll()
				).formLogin(f -> f.permitAll().defaultSuccessUrl("/")
				).logout(l -> l.logoutSuccessUrl("/login")
				).build();
				
	}
}
