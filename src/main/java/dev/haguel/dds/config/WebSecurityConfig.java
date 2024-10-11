package dev.haguel.dds.config;

import dev.haguel.dds.enumeration.Roles;
import dev.haguel.dds.service.UserDetailsServiceImpl;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        String adminRole = Roles.ROLE_ADMIN.name();
        String dispatcherRole = Roles.ROLE_DISPATCHER.name();

        List<String> authEndpoints = EndPoints.getAuthEndpoints();
        for (String endpoint : authEndpoints) {
            http.authorizeRequests().mvcMatchers(endpoint).permitAll();
        }

        List<String> adminEndpoints = EndPoints.getAdminEndpoints();
        for (String endpoint : adminEndpoints) {
            http.authorizeRequests().mvcMatchers(endpoint)
                    .access("hasAnyRole('" + adminRole + "')");
        }

        List<String> dispatcherEndpoints = EndPoints.getDispatcherEndpoints();
        for (String endpoint : dispatcherEndpoints) {
            http.authorizeRequests().mvcMatchers(endpoint)
                    .access("hasAnyRole('" + adminRole + "', '" + dispatcherRole + "')");
        }

        http.authorizeRequests().and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage(EndPoints.GET_LOGIN_PAGE)
                .defaultSuccessUrl(EndPoints.GET_MAIN_PAGE)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password").and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }
}
