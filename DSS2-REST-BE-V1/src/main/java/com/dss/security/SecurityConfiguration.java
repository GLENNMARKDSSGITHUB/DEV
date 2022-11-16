/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.security;

import com.dss.repository.user.UsersRepository;
import com.dss.util.enums.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class is a security configuration for Login and Authentication
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DssUserDetailsServiceImpl dssUserDetailsService;

    private final UsersRepository usersRepository;

    /**
    * Constructor for SecurityConfiguration class
     * @see #SecurityConfiguration(DssUserDetailsServiceImpl, UsersRepository)
    */
    public SecurityConfiguration(DssUserDetailsServiceImpl dssUserDetailsService, UsersRepository usersRepository) {
        this.dssUserDetailsService = dssUserDetailsService;
        this.usersRepository = usersRepository;
    }

    /**
     * The authentication manager I added by overriding
     * @see #configure(AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /** Performs authentication with the same contract as AuthenticationManager.authenticate(Authentication) .
     * @return a fully authenticated object including credentials.
     * @see #authenticationProvider()
     */
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(dssUserDetailsService);
        return daoAuthenticationProvider;
    }

    /** Configures the authorization for the URL such as things like if it requires to be authenticated
     *  or if only certain roles can access it etc. It only has effect for those URLs that are processed by
     *  that SecurityFilterChain (i.e. Those URLs that are matched by requestMatchers()).
     *  I added also jwt filters (1. authentication, 2. authorization)
     * @see #configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/API/login.do", "/API/token/generate-token.do")
                .permitAll()
                .antMatchers("/API/registration/**")
                .hasAnyAuthority(UserRoles.ROLE_SUPER_ADMIN.getStrRole(), UserRoles.ROLE_ADMIN.getStrRole())
                .antMatchers("/API/movie-catalogue/**")
                .hasAnyAuthority(UserRoles.ROLE_SUPER_ADMIN.getStrRole(), UserRoles.ROLE_ADMIN.getStrRole())
                .antMatchers("/API/reviews/**")
                .hasAnyAuthority(UserRoles.ROLE_SUPER_ADMIN.getStrRole(), UserRoles.ROLE_ADMIN.getStrRole(),UserRoles.ROLE_USER.getStrRole())
                .antMatchers("/API/actor/**")
                .hasAnyAuthority(UserRoles.ROLE_SUPER_ADMIN.getStrRole(), UserRoles.ROLE_ADMIN.getStrRole())
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.usersRepository));
    }

    /** Returns a BCryptPasswordEncoder with the strength of 12
     * param strength - the log rounds to use, between 4 and 31 (in this project, I use strength of 12)
     * @return BCryptPasswordEncoder with the strength of 12
     * @see #passwordEncoder()
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
