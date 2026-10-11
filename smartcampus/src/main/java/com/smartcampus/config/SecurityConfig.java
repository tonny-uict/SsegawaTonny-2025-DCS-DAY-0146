package com.smartcampus.config;

import com.smartcampus.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService uds) { this.userDetailsService = uds; }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/h2-console/**", "/api/**").permitAll()
                .requestMatchers("/", "/login", "/register").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/lecturer/**").hasAnyRole("LECTURER", "ADMIN")
                .requestMatchers("/student/**").hasAnyRole("STUDENT", "ADMIN", "LECTURER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            )
            .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
            .userDetailsService(userDetailsService)
            // for H2 console
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/api/**"))
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
