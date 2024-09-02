package com.example.blog_security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.blog_security.Service.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final MyUserDetailsService myUserDetailsService; 

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); 

        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider; 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authenticationProvider(daoAuthenticationProvider())
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/register").permitAll()

            .requestMatchers(
                "/api/v1/auth/get-all-users",
                "/api/v1/auth/get-user-by-id**",
                "/api/v1/auth/get-user-by-username**",
                "/api/v1/auth/delete-user**",
                "/api/v1/auth/update-user**", 
                "/api/v1/blog/get-all-posts", 
                "/api/v1/blog/delete-post"
            ).hasAuthority("ADMIN")

            .requestMatchers(
                "/api/v1/auth/update-user**",
                "/api/v1/blog/get-my-posts",
                "/api/v1/blog/get-post-by-id**", 
                "/api/v1/blog/get-post-by-title**", 
                "/api/v1/blog/edit-post**",
                "/api/v1/blog/delete-post**"

            ).hasAuthority("USER")

            .anyRequest().authenticated()
            .and()
            .logout().logoutUrl("/api/v1/auth/logout")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .and()
            .httpBasic();
        return http.build(); 
    }
}
