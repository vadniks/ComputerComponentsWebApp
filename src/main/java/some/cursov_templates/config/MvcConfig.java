package some.cursov_templates.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static some.cursov_templates.Constants.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(ENDPOINT_INDEX);
        registry.addViewController(ENDPOINT_BROWSE);
        registry.addViewController(ENDPOINT_LOGIN);
        registry.addViewController(ENDPOINT_REGISTER);
        registry.addViewController(ENDPOINT_ADMIN);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_STATIC)
            .addResourceLocations(RESOURCE_STATIC_LOCATION);
        registry.addResourceHandler(RESOURCE_BACK_END)
            .addResourceLocations(RESOURCE_BACK_END_LOCATION);
    }
}
