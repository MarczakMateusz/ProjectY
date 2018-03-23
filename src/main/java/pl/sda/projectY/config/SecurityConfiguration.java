package pl.sda.projectY.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.projectY.handler.SuccessLoginHandler;

/**
 * @author trutyna
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SuccessLoginHandler successLoginHandler;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(SuccessLoginHandler successLoginHandler, @Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.successLoginHandler = successLoginHandler;
        this.userDetailsService = userDetailsService;
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/main**").permitAll()
                //.antMatchers("/panelAdmin/**").access("hasRole('ADMIN')")
                //.antMatchers("/panelInstructor/**").access("hasRole('INSTRUCTOR')")
                //.antMatchers("/panelStudent/**").access("hasRole('STUDENT')")
                .and()
                .formLogin()
                .loginPage("/main")
                .usernameParameter("login")
                .loginProcessingUrl("/signin")
                .passwordParameter("password")
                .permitAll()
                .successHandler(successLoginHandler)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error");

    }
}
