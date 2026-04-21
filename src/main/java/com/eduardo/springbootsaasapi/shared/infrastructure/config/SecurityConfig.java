package com.eduardo.springbootsaasapi.shared.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.JwtAccessDeniedHandler;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.JwtAuthEntryPoint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final JwtAuthEntryPoint jwtAuthEntryPoint;
        private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

        private static final String[] PUBLIC = {
                        "/api/auth/login",
                        "/api/auth/register",
                        "/api/auth/validate"
        };

        private static final String[] SALES_MODULES = {
                        "/api/users/**",
                        "/api/roles/**",
                        "/api/groups/**",
                        "/api/products/**",
                        "/api/dashboard/**",
                        "/api/forecast/**"
        };

        public SecurityConfig(
                        JwtAuthFilter jwtAuthFilter,
                        JwtAuthEntryPoint jwtAuthEntryPoint,
                        JwtAccessDeniedHandler jwtAccessDeniedHandler) {
                this.jwtAuthFilter = jwtAuthFilter;
                this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
                this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(cors -> {
                                })
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                                .accessDeniedHandler(jwtAccessDeniedHandler))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(PUBLIC).permitAll()
                                                .requestMatchers(SALES_MODULES)
                                                .hasAnyRole("ROOT", "VP", "DIRECTOR", "MANAGER", "SALES")
                                                .anyRequest().authenticated())
                                // Agregar nuestro filtro JWT ANTES del filtro de Spring
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
