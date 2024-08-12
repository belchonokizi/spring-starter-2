package com.dmdev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.dmdev.spring.database.entity.Role.ADMIN;

@Configuration
@EnableMethodSecurity /*чтобы сработала аннотация PreAuthorize*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(urlConfig -> urlConfig
                        .antMatchers("/login", "/users/registration",
                                "/v3/api-docs/**", "/").permitAll()
                        .antMatchers("/users/{\\d+}/delete").hasAuthority(ADMIN.getAuthority())
                        //для url c admin проверка на роль
                        .antMatchers("/admin/**").hasAuthority(ADMIN.getAuthority())
                        //для остальных - проверка на аутентификацию
                        .anyRequest().authenticated()
                )
//                .httpBasic(Customizer.withDefaults());
//                переопределяем свою страничку логина
                .logout(logout -> logout
                        //действия ниже с logout происходят по умолчанию
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .formLogin(login -> login.loginPage("/login")
                        .defaultSuccessUrl("/users")
                        //даем разрешение для логина всем
                        .permitAll());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
