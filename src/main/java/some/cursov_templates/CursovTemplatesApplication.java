package some.cursov_templates;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Properties;

import static some.cursov_templates.Constants.*;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class CursovTemplatesApplication {

    public static void main(String... args) {
        val app = new SpringApplication(CursovTemplatesApplication.class);

        val props = new Properties();
        props.setProperty(DB_INIT_SCHEMA_PROP, DB_INIT_SCHEMA);
        app.setDefaultProperties(props);

        app.run();

        System.out.println("""
               ####      ##                         ##                  ##
              ##  ##    ##                         ##                   ##
              ##     #######     #####   ## ### #######     ####    ######
               ##      ##      ##   ##  ####      ##      ##   ##  ##  ##
                ##    ##      ##    ##  ##       ##      ######## ##   ##
            ##  ##    ##      ##  ###  ##        ##      ##       ##  ##
             ####      ###     ### ##  ##         ###     ####     #####
            """.stripIndent());
    }
}
