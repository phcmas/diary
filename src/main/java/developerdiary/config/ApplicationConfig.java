package developerdiary.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"developerdiary.dao", "developerdiary.service"})
public class ApplicationConfig {
}
