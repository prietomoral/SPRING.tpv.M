package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import api.Uris;
import entities.users.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()//
                .antMatchers(HttpMethod.GET, Uris.SERVLET_MAP + Uris.VERSION + Uris.ADMINS).permitAll()//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.TOKENS + "/**").authenticated()//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.ADMINS + "/**").hasRole(Role.ADMIN.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.EMBROIDERIES + "/**").hasRole(Role.MANAGER.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.TEXTILE_PRINTINGS + "/**").hasRole(Role.MANAGER.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.ARTICLES + "/**").hasRole(Role.MANAGER.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.ALERTS + "/**").hasRole(Role.MANAGER.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.ALERTSFAMILY + "/**").hasRole(Role.MANAGER.name())//
                .antMatchers(HttpMethod.POST, Uris.SERVLET_MAP + Uris.VERSION + Uris.USERS + "/**").hasRole(Role.ADMIN.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.JOB).hasRole(Role.ADMIN.name())//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.INVOICES + Uris.POPULATE).permitAll()//
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.INVOICES + "/**")
                .hasAnyRole(Role.MANAGER.name(), Role.ADMIN.name())//
                .antMatchers(HttpMethod.GET, Uris.SERVLET_MAP + Uris.VERSION + Uris.USERS + "/**")
                .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())//
                .antMatchers(HttpMethod.PUT, Uris.SERVLET_MAP + Uris.VERSION + Uris.USERS + "/**")
                .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())//
                .antMatchers(HttpMethod.POST, Uris.SERVLET_MAP + Uris.VERSION + Uris.CUSTOMERS + "/**")
                .hasAnyRole(Role.MANAGER.name(), Role.OPERATOR.name())//
                .and().httpBasic();//
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
