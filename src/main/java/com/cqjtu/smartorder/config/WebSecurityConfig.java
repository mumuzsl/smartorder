package com.cqjtu.smartorder.config;

import com.cqjtu.smartorder.global.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author mumu
 * @date 2020/7/24
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler();
    private final AuthenticationFailureHandler failureHandler = new AuthenticationFailureHandler();
    private final CaptchaFilter captchaFilter = new CaptchaFilter();

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    public static final int DEV_MODE = 0;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (DEV_MODE != 0) {
            http
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
        } else {
            config(http);
        }
    }

    public static int devModeRoleId(int roleId) {
        return DEV_MODE == 0 ? roleId : DEV_MODE;
    }

    private void config(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/login")
                .permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().logout().permitAll()
                .and().csrf().disable();

        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

//        http.sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(
                "/static/**",
                "/js/**",
                "/css/**",
                "/images/**",
                "/lib/**",
                "/page/**",
                "/api/captcha",
                "/graphql",
                "/login"
        );
    }
}
