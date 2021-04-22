package diary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/aa").hasRole("ADMIN")
                .antMatchers("/bb").hasRole("USER")
                .anyRequest().permitAll();
        http.formLogin()
                .loginPage("/user/login") // login page
                .loginProcessingUrl("/user/auth")
                .failureUrl("/")
                .defaultSuccessUrl("/", true)
                .usernameParameter("id")
                .passwordParameter("password").permitAll();
        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

}
