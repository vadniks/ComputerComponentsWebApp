package some.cursov_templates;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Properties;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class CursovTemplatesApplication {

    public static void main(String... args) {
        val app = new SpringApplication(CursovTemplatesApplication.class);

        val props = new Properties();
//        props.setProperty("spring.jpa.generate-ddl", "true");
//        props.setProperty("spring.jpa.hibernate.ddl-auto", "update");
        props.setProperty("spring.session.jdbc.initialize-schema", "always");
        props.setProperty("debug", "true");
        app.setDefaultProperties(props);

        app.run();
    }
}
