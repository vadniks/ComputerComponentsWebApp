package some.cursov_templates.config;

import org.springframework.context.annotation.Configuration;
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
        registry.addViewController(ENDPOINT_ADMINISTRATE);
    }
}
