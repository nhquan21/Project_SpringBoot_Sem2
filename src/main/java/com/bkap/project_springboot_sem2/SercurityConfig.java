package com.bkap.project_springboot_sem2;

import com.bkap.project_springboot_sem2.services.CustomeAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SercurityConfig {
    private CustomeAccountDetailService customeAccountDetailService;

    @Autowired
    public SercurityConfig(CustomeAccountDetailService customeAccountDetailService) {
        this.customeAccountDetailService = customeAccountDetailService;
    }


    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((auth)->auth.
                requestMatchers("/*").permitAll().

                requestMatchers("/admin/*").hasAnyAuthority("ADMIN").
                anyRequest().permitAll()
        ).formLogin(login->login.loginPage("/login").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/admin", true));


        return http.build();

    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        System.out.println("11111");
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((auth)->auth.
                        requestMatchers("/*").permitAll()).
                formLogin(login->login.loginPage("/dang-nhap").loginProcessingUrl("/dang-nhap")
                        .usernameParameter("username").passwordParameter("password")
                        .defaultSuccessUrl("/home", true));
        return http.build();
    }



    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->web.ignoring().requestMatchers("/static/**", "/assets/**", "/uploads/**", "/users-assets/**");
    }

}

