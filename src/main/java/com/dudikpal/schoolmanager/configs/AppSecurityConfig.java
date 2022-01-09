/*
package com.dudikpal.schoolmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/health/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/students", true)
                .and()
                .csrf().disable();
    }




}
*/
