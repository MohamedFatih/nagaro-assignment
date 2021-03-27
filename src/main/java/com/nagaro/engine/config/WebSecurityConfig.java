package com.nagaro.engine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Configuring http security chain");
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/css/**","/webjars/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login?logout")
                    .permitAll()
                    .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("Configuring in memory Authentication");
        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}