package some.cursov_server.config;

import com.vaadin.flow.spring.SpringServlet;
import com.vaadin.flow.spring.VaadinMVCWebAppInitializer;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Collection;
import java.util.Collections;

public class AppInitializer extends VaadinMVCWebAppInitializer
    implements WebApplicationInitializer {

    @Override
    protected Collection<Class<?>> getConfigurationClasses() {
        return Collections.singletonList(SharedConfiguration.class);
    }

    @Override
    public void onStartup(@NotNull ServletContext servletContext) {
        val context = new AnnotationConfigWebApplicationContext();
        registerConfiguration(context);
        servletContext.addListener(new ContextLoaderListener(context));

        val registration = servletContext.addServlet(
            "dispatcher",
            new SpringServlet(context, true));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }

    @Override
    protected void registerConfiguration(AnnotationConfigWebApplicationContext context) {
        super.registerConfiguration(context);
        // For the future
    }
}
