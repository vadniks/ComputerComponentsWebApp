package some.cursov_server;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import java.util.Properties;

@SpringBootApplication
public class CursovServerApplication implements WebApplicationInitializer {

    public static void main(String... args) {
        val app = new SpringApplication(CursovServerApplication.class);
        val props = new Properties(); // For the future
        app.setDefaultProperties(props);
        app.run(args);
    }

    @Override
    public void onStartup(@NotNull ServletContext servletContext) {

    }
}
