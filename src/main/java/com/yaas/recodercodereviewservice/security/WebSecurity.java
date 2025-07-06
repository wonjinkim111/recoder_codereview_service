package com.yaas.recodercodereviewservice.security;

import com.yaas.recodercodereviewservice.security.AuthorizationFilter;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class WebSecurity extends WebSecurityConfigurerAdapter {
    Environment env;

    @Autowired
    public WebSecurity(Environment env) {
        this.env = env;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[]{"/**"})).permitAll();
        http.headers().frameOptions().disable();
    }
}
