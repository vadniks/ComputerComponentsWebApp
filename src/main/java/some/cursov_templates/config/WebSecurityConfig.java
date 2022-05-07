package some.cursov_templates.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import some.cursov_templates.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                .ignoringAntMatchers(POST_SELECT, POST_CLEAR)
                .and()
            .authorizeRequests()
                .antMatchers(
                    ENDPOINT_INDEX,
                    ENDPOINT_BROWSE,
                    ENDPOINT_REGISTER,
                    ENDPOINT_ABOUT,
                    RESOURCE_STATIC,
                    RESOURCE_BACK_END,
                    GET_COMPONENT,
                    POST_SELECT,
                    POST_CLEAR).permitAll()
                .antMatchers(ENDPOINT_ADMIN).hasRole(User.Role.ADMIN.mkRole())
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .usernameParameter(ENTITY_NAME)
                .passwordParameter(USER_PASSWORD)
                .loginPage(ENDPOINT_LOGIN)
                .failureUrl(ENDPOINT_LOGIN + "?error=true")
                .successForwardUrl(ENDPOINT_INDEX)
                .permitAll()
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
