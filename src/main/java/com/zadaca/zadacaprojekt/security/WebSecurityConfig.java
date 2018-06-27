package com.zadaca.zadacaprojekt.security;


import com.zadaca.zadacaprojekt.domain.Permissions;
import com.zadaca.zadacaprojekt.service.UserManagerImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserManagerImplementation userManagerImpl;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/login/rest");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/owner/list").hasAuthority(Permissions.VIEW_OWNER_LIST.name())
                .antMatchers("/car/list").hasAuthority(Permissions.VIEW_CAR_LIST.name())
                .antMatchers("/car/save").hasAuthority(Permissions.CREATE_CAR.name())
                .antMatchers("/owner/save").hasAuthority(Permissions.CREATE_OWNER.name())
                .antMatchers("/car/delete").hasAuthority(Permissions.DELETE_CAR.name())
                .antMatchers("/owner/delete").hasAuthority(Permissions.DELETE_OWNER.name())
                .antMatchers("/car/edit").hasAuthority(Permissions.EDIT_CAR.name())
                .antMatchers("/owner/edit").hasAuthority(Permissions.EDIT_OWNER.name())

                .antMatchers("/**").permitAll();
    }

    @Bean
    public LoggerListener loggerListener() {
        return new LoggerListener();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userManagerImpl)
                .passwordEncoder(passwordEncoder());
    }

}
