/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author User
 */
@Configuration
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
         return (web) -> web.ignoring().requestMatchers("/images/**", "/vendor/**", "/css/**");
    }
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/administrador/**").hasRole("ADMIN")
                .requestMatchers("/productos/**").hasRole("ADMIN")
                .requestMatchers("/","/search","/usuario/registro","usuario/save").permitAll()
                .anyRequest().authenticated());
        http.formLogin(form -> form
			.loginPage("/usuario/login")
			.permitAll()
                        .successHandler((request, response, authentication) -> {
                        response.sendRedirect("/usuario/acceder");
                        })
        );
        http.logout(logout -> logout
                .logoutUrl("/usuario/logout")
                .logoutSuccessUrl("/cerrar").permitAll());
        return http.build();
    }
    @Bean 
    UserDetailsServiceImpl userDetailsService(){
        return new UserDetailsServiceImpl();
    }
    @Bean 
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
