package some.cursov_templates.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import some.cursov_templates.service.UserService;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.entity.User.Role.*;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @ImplicitAutowire
    private final UserService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .ignoringAntMatchers(
                    POST_SELECT,
                    POST_CLEAR,
                    ENDPOINT_LOGIN,
                    POST_LOGOUT)
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
                    POST_CLEAR,
                    ENDPOINT_ERROR).permitAll()
                .antMatchers(POST_LOGOUT).hasRole(USER.mkRole())
                .antMatchers(ENDPOINT_ADMIN, POST_LOGOUT).hasRole(ADMIN.mkRole())
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .usernameParameter(ENTITY_NAME)
                .passwordParameter(USER_PASSWORD)
                .loginPage(ENDPOINT_LOGIN)
                .failureUrl(FROM_LOGIN_TO_LOGIN_WITH_ERROR)
                .defaultSuccessUrl(ENDPOINT_INDEX)
                .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(POST_LOGOUT))
                .logoutSuccessUrl(ENDPOINT_INDEX)
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        val provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(EMPTY);
    }
}
