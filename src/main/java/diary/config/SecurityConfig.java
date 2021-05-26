package diary.config;

import diary.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    public int hashCode() {
        return Objects.hash(customUserDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/welcome").hasRole("USER")
                .antMatchers("/**").permitAll()
                .antMatchers("/ss").hasRole("ADMIN")
                .anyRequest().permitAll();
        http.formLogin()
                .loginProcessingUrl("/user/authenticate")
                .defaultSuccessUrl("/main", true)
                .usernameParameter("name")
                .passwordParameter("password").permitAll();
        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

}
