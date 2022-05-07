package some.cursov_templates.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @ImplicitAutowire
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .ignoringAntMatchers(ENDPOINT_SELECT, ENDPOINT_CLEAR)
                .and()
            .authorizeRequests()
                .antMatchers(
                    ENDPOINT_INDEX,
                    ENDPOINT_BROWSE,
                    ENDPOINT_REGISTER,
                    RESOURCE_STATIC,
                    RESOURCE_BACK_END,
                    ENDPOINT_COMPONENT,
                    ENDPOINT_SELECT,
                    ENDPOINT_CLEAR).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(ENDPOINT_LOGIN).permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
}
