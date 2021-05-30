package diary.config;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"diary.controller"})
public class DispatcherServletConfig implements WebMvcConfigurer {

    private static final int SEC_IN_A_YEAR = 60 * 60 * 24 * 365;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //cache period 31556926
        registry.addResourceHandler("/css/**","/user/css/**","/projects/css/**",
                                    "/projects/{id}/css/**", "/algorithm/css/**",
                                    "/algorithm/{id}/css/**")
                .addResourceLocations("/static/css/").setCachePeriod(SEC_IN_A_YEAR);
        registry.addResourceHandler("/img/**","/projects/img/**", "/algorithm/img/**")
                .addResourceLocations("/static/img/").setCachePeriod(SEC_IN_A_YEAR);
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/static/js/").setCachePeriod(SEC_IN_A_YEAR);
        registry.addResourceHandler("/user/js/**")
                .addResourceLocations("/static/js/user/").setCachePeriod(SEC_IN_A_YEAR);
        registry.addResourceHandler("/projects/js/**","/projects/{id}/js/**")
                .addResourceLocations("/static/js/projects/").setCachePeriod(SEC_IN_A_YEAR);
        registry.addResourceHandler("/algorithm/js/**","/algorithm/{id}/js/**")
                .addResourceLocations("/static/js/algorithm/").setCachePeriod(SEC_IN_A_YEAR);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/user/signin");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                String method = request.getMethod().toLowerCase();
                if (!"put".equals(method) && !"post".equals(method)) {
                    return false;
                } else {
                    String contentType = request.getContentType();
                    if (contentType == null) {
                        return false;
                    } else {
                        return contentType.toLowerCase().startsWith("multipart/");
                    }
                }
            }
        };
    }

    @Bean
    public HandlebarsViewResolver handlebarsViewResolver() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".hbs");
        return viewResolver;
    }


}
