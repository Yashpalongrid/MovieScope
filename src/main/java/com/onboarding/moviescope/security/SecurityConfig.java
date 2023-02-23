package com.onboarding.moviescope.security;

import com.onboarding.moviescope.filter.CustomAuthenticationFilter;
import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter=new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
//        customAuthenticationFilter.setFilterProcessesUrl("/user/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/user/login/**").permitAll();  //we can do this for home page,login.register
        http.authorizeRequests().antMatchers("/user").permitAll();
        http.authorizeRequests().antMatchers("/movie").permitAll();
        http.authorizeRequests().antMatchers("/movie/{id}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/user/**").hasAnyAuthority("READ_AUTHORITY");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/user/**").hasAnyAuthority("WRITE_PRIVILEGE");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/movie/**").hasAnyAuthority("READ_AUTHORITY");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/admin/movie/**").hasAnyAuthority("WRITE_PRIVILEGE");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/review/**").hasAnyAuthority("READ_AUTHORITY");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/review/**").hasAnyAuthority("WRITE_PRIVILEGE");


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests()
//                .antMatchers("/user").hasAnyRole("USER","ADMIN")
//                .antMatchers("/").permitAll()
//                .and().formLogin();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


}
