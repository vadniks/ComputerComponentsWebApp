package some.cursov_server;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Properties;

@EntityScan("some.cursov_server.*")
@SpringBootApplication
public class CursovServerApplication {

    public static void main(String... args) {
        val app = new SpringApplication(CursovServerApplication.class);
        val props = new Properties();
        props.setProperty("debug", "true");
        app.setDefaultProperties(props);
        app.run(args);
    }
}
