package com.notahmed.springsecurityjpa.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {



    // setting up the userdetails service to take my service instead
    @Bean
    public UserDetailsService userDetailsService() {


        // using lambda
        // loadUsernameByPassword
//        return username -> new MyUserDetails(username);

        // also same thing
//        return MyUserDetails::new;


        return new MyUserDetailsService();
    }


    // configuring the provider to take my encoder and userdetails service
    // TODO read more about DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }














    // doing the request mapping for roles
    // Authorization
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/").permitAll()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
