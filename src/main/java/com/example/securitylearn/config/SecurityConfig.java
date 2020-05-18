package com.example.securitylearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**", "/captcha.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/myLogin.html").permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("123").roles("USER")
                .and()
                .withUser("admin").password("123").roles("ADMIN");
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(userDetailsService);
////
////        auth.inMemoryAuthentication()
////                //针对上方authentication设置密码匹配规则
////                .passwordEncoder(new MyPasswordEncoder())
////
////                .withUser("cy")
////                .password("pwdd")
////                .authorities("ROLE_USER")
////                .and()
////                .withUser("sj")
////                .password("pwdd")
////                .authorities("ROLE_USER");
////
////        auth.jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery("")
////                .authoritiesByUsernameQuery("")
////                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
//
//
//    }
}

