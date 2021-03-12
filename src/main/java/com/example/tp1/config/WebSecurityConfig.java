package com.example.tp1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private com.example.tp1.config.JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public com.example.tp1.config.JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new com.example.tp1.config.JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/token/*", "/signup").permitAll()
                .antMatchers("/token/*", "/users").permitAll()
                .antMatchers("/token/*", "/userdelete/{id}").permitAll()
                .antMatchers("/token/*", "/adduser").permitAll()
                .antMatchers("/token/*", "/userupdate/{id}").permitAll()
                .antMatchers("/token/*", "/user/{id}").permitAll()
                .antMatchers("/token/*", "/api/joueurdelete/{id}").permitAll()
                .antMatchers("/token/*", "/api/joueurupdate/{id}").permitAll()
                .antMatchers("/token/*", "/api/joueuradd").permitAll()
                .antMatchers("/token/*", "/api/joueur/{id}").permitAll()
                .antMatchers("/token/*", "/api/equipe/{id}").permitAll()
                .antMatchers("/token/*", "/api/equipeadd").permitAll()
                .antMatchers("/token/*", "/api/equipedelete/{id}").permitAll()
                .antMatchers("/token/*", "/api/equipeupdate/{id}").permitAll()
                .antMatchers("/token/*", "/api/*").permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
