package org.altervista.scarrozzo.jwtexample.config;

import org.altervista.scarrozzo.jwtexample.security.CustomAuthenticationFilter;
import org.altervista.scarrozzo.jwtexample.security.CustomAuthenticationProvider;
import org.altervista.scarrozzo.jwtexample.security.CustomAuthorizationFilter;
import org.altervista.scarrozzo.jwtexample.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.altervista.scarrozzo.jwtexample.controller.CustomUserController.REGISTRATION_URL;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationProvider customAuthProvider;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService,
                                 CustomAuthenticationProvider customAuthProvider) {
        this.userDetailsService = userDetailsService;
        this.customAuthProvider = customAuthProvider;
    }

    public @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    protected @Override void configure(AuthenticationManagerBuilder auth) throws Exception {
        // example of internal authentication with db user entity
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // example of external authentication
        auth.authenticationProvider(customAuthProvider);
    }

    protected @Override void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers(POST, REGISTRATION_URL).permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager()))
                .addFilter(new CustomAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(STATELESS);
    }

    public @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
