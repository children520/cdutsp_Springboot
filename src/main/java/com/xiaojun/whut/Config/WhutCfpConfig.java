package com.xiaojun.whut.Config;

import com.xiaojun.whut.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WhutCfpConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/app/user/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/db/**").hasRole("DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

                    }
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

                    }
                })
                .and()
                .csrf().disable();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
        String hierarchy="role_dba > role_admin role_admin > role_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
//        //manager.setDataSource(dataSource);
//        if(!manager.userExists("user")){
//            manager.createUser(User.withUsername("user").password("123").roles("USER").build());
//        }
//        if(!manager.userExists("admin")){
//            manager.createUser(User.withUsername("admin").password("123").roles("ADMIN","USER").build());
//        }
//        return manager;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user")
//                .password(new BCryptPasswordEncoder().encode("123")).roles("USER");
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("123")).roles("admin");

    }


//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
}
