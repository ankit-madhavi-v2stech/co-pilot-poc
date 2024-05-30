package com.quiz.mgmt.config;

import com.quiz.mgmt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

//@Configuration
//@EnableWebSecurity
public class SecurityConfigurer
//        extends SecurityConfigurerAdapter
{

//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Autowired
//    private AuthenticationManagerBuilder authenticationManagerBuilder;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/authenticate").permitAll()
////                                .anyRequest().authenticated()
////                )
////                .oauth2ResourceServer(oauth2ResourceServer ->
////                        oauth2ResourceServer
////                                .jwt(jwt ->
////                                        jwt.decoder(jwtDecoder())
////                                )
////                );
//    }
//
////    @Bean
////    public JwtDecoder jwtDecoder() {
////        String jwkSetUri = "your-jwk-set-uri"; // replace with your JWK Set URI
////        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return authenticationManagerBuilder.build();
//    }
}