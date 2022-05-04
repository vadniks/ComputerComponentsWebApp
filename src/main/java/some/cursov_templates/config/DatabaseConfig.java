package some.cursov_templates.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

import static some.cursov_templates.Constants.*;

@EnableJpaRepositories(basePackages = {PACKAGE})
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        val a = new HikariConfig();
        a.setJdbcUrl(JDBC_URL);
        a.setDriverClassName(DRIVER_NAME);
        a.setUsername(DB_USERNAME);
        a.setPassword(DB_PASSWORD);
        return new HikariDataSource(a);
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory(DataSource b) {
        val a = new LocalSessionFactoryBean();
        a.setDataSource(b);
        a.setPackagesToScan(PACKAGES);

        val c = new Properties();
        c.setProperty(DB_DIALECT_PROP, DB_DIALECT);
        c.setProperty(DB_HBM_PROP, DB_HBM);
        a.setHibernateProperties(c);
        return a;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalSessionFactoryBean a) {
        return new HibernateTransactionManager(
            Objects.requireNonNull(a.getObject()));
    }
}
