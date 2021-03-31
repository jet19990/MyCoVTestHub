package com.mycovtest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminDetailService userDetailsService;
    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SimpleUrlAuthenticationFailureHandler myFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(AdminUserDetails.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // <5>
        http
                .authorizeRequests()
                .antMatchers("/built/**", "/main.css").permitAll()
                .antMatchers("/admin/**").authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(myFailureHandler)
                .and()
                .csrf().disable()
                .logout();
    }

    @Bean
    protected SimpleUrlAuthenticationFailureHandler getFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

}
