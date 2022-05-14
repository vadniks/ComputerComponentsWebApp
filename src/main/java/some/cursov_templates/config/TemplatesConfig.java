package some.cursov_templates.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Configuration
public class TemplatesConfig {
    @ImplicitAutowire
    private final ApplicationContext context;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        val resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(context);
        resolver.setPrefix(TEMPLATE_PREFIX);
        resolver.setSuffix(TEMPLATE_POSTFIX);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        val engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.setEnableSpringELCompiler(true);
        engine.setMessageSource(messageSource());
        return engine;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        val source = new ResourceBundleMessageSource();
        source.setBasename(TEMPLATE_MESSAGES_NAME);
        source.setDefaultEncoding(UTF_8);
        return source;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        val resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(1);
        resolver.setViewNames(new String[]{TEMPLATE_POSTFIX});
        return resolver;
    }
}
