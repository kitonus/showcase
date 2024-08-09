package com.jatis.showcase.config;

import java.util.TimeZone;

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

import com.jatis.showcase.filter.JwtAuthenticationFilter;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
	@Bean
	AuthenticationProvider authenticationProvider(PasswordEncoder pEnc, UserDetailsService userDetailsServ) {
		DaoAuthenticationProvider daoAuthProv = new DaoAuthenticationProvider();
		daoAuthProv.setPasswordEncoder(pEnc);
		daoAuthProv.setUserDetailsService(userDetailsServ);
		return daoAuthProv;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, 
			AuthenticationProvider authProv, JwtAuthenticationFilter jwtFilter) throws Exception {
		return http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(r -> r.requestMatchers("/auth/**")
					.permitAll().requestMatchers(
					"/swagger-ui.html",
					"/swagger-ui/**", "/v3/api-docs/**", "/actuator/health").permitAll()
					.anyRequest().authenticated())
			.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authProv)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}

	@PostConstruct
	void showDefaultTimezone() {
		TimeZone defaultTz = TimeZone.getDefault();
		log.info("->Default Timezone: {} ({})",  defaultTz.getID(), defaultTz.getDisplayName());
	}
}
