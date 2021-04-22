package diary.config;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "diary.controller")
public class DispatcherServletConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //cache period 31556926
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/static/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/main");
    }

    //@Bean
    //public InternalResourceViewResolver getInternalResourceViewResolver() {
    //    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    //    resolver.setPrefix("/WEB-INF/");
    //    resolver.setSuffix(".hbs");
    //    return resolver;
    //}

    @Bean
    public HandlebarsViewResolver handlebarsViewResolver() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".hbs");
        return viewResolver;
    }


}
