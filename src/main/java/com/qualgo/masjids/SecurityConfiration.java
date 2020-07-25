package com.qualgo.masjids;

import com.qualgo.masjids.services.UserService;
import com.qualgo.masjids.services.impl.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private DefaultUserService defaultUserService;


    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(defaultUserService).passwordEncoder(bCryptPasswordEncoder());

    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        // httpSecurity.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/sign-up").hasAnyRole();
       //  httpSecurity.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()

        ;
    }


}
