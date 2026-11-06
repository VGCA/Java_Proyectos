package com.portfolio.ecommerce.infrastructure.config;

import com.portfolio.ecommerce.infrastructure.adapter.in.security.LoginExitosoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

/**
 * Spring Security 7: ya no existe WebSecurityConfigurerAdapter ni antMatchers.
 * Se declara un bean SecurityFilterChain con la DSL de lambdas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, LoginExitosoHandler loginExitoso) throws Exception {
        http
                // Igual que el proyecto original. Ver nota en el README sobre CSRF + formularios multipart.
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/administrador/**", "/productos/**").hasRole("ADMIN")
                        .requestMatchers("/usuario/compras", "/usuario/detalle/**", "/order", "/saveOrder")
                        .authenticated()
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/usuario/login")
                        .loginProcessingUrl("/usuario/acceder")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(loginExitoso)
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(
                                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/usuario/cerrar"))
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
