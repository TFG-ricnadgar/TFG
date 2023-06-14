package etsii.tfg.DungeonRaiders.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import etsii.tfg.DungeonRaiders.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/user/update").authenticated()
                .antMatchers("/user/stats").authenticated()
                .antMatchers("/user/globalstats").authenticated()
                .antMatchers("/user/register").anonymous()
                .antMatchers("/game/**").authenticated()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .loginPage("/user/login").failureUrl("/user/login")
                .permitAll()
                .and()
                .logout().logoutUrl("/user/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "GET")).permitAll();

        // Configuración para que funcione la consola de administración
        // de la BD H2 (deshabilitar las cabeceras de protección contra
        // ataques de tipo csrf y habilitar los framesets si su contenido
        // se sirve desde esta misma página.
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();
    }

}