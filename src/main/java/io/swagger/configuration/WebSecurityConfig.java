package io.swagger.configuration;

import io.swagger.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    private static final String[] AUTH_WHITELIST = {
            "/users/login",
            "/h2-console/**/**",
            "/swagger-ui/**/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/api-docs",
            "webjars/**"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // No CSRF protection needed
        http.csrf().disable();
        // No Sessions needed
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                // Disallow endpoints for unauthenticated users
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/users").authenticated()
                .antMatchers("/users/**/*").authenticated()
                .antMatchers("/account").authenticated()
                .antMatchers("/account/**/*").authenticated()
                .antMatchers("/transaction").authenticated()
                .antMatchers("/transaction/**/*").authenticated()

                // Allow all other endpoints
                .anyRequest().permitAll();

        // Add the jwt filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
