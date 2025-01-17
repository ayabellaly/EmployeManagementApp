package com.example.EmployeeManagementApp.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Updated way to disable CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/employees/**").hasAnyRole("ADMINISTRATOR", "HR_PERSONNEL", "MANAGER")
                        .requestMatchers("/api/employees/admin/**").hasRole("ADMINISTRATOR")
                        .requestMatchers("/api/employees/hr/**").hasRole("HR_PERSONNEL")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // Updated way to configure HTTP Basic
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(encoder.encode("password"))
                        .roles("ADMINISTRATOR")
                        .build(),
                User.withUsername("hr")
                        .password(encoder.encode("password"))
                        .roles("HR_PERSONNEL")
                        .build(),
                User.withUsername("manager")
                        .password(encoder.encode("password"))
                        .roles("MANAGER")
                        .build()
        );
    }
}
