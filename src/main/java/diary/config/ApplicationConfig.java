package diary.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"diary.aspect","diary.dao", "diary.service"})
@Import({DBConfig.class})
public class ApplicationConfig {
}
