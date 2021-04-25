package diary.config;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "diary.controller")
public class DispatcherServletConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //cache period 31556926
        registry.addResourceHandler("/css/**","/user/css/**").addResourceLocations("/static/css/").setCachePeriod(0);
        registry.addResourceHandler("/img/**").addResourceLocations("/static/img/").setCachePeriod(0);
        registry.addResourceHandler("/js/**","/user/js/**").addResourceLocations("/static/js/").setCachePeriod(0);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/signin");
    }

    //@Bean
    //public InternalResourceViewResolver getInternalResourceViewResolver() {
    //    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    //    resolver.setPrefix("/WEB-INF/");
    //    resolver.setSuffix(".jsp");
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
