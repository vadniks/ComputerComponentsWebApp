package some.cursov_server;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class CursovServerApplication {

    public static void main(String... args) {
        val app = new SpringApplication(CursovServerApplication.class);
        val props = new Properties(); // For the future
        app.setDefaultProperties(props);
        app.run(args);
    }
}
