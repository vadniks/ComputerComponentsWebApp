package some.cursov_server.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import some.cursov_server.Constants;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@EnableJpaRepositories
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        val a = new HikariConfig();
        a.setJdbcUrl(Constants.JDBC_URL);
        a.setDriverClassName(Constants.DRIVER_NAME);
        a.setUsername(Constants.DB_USERNAME);
        a.setPassword(Constants.DB_PASSWORD);
        return new HikariDataSource(a);
    }

    @Bean
    public LocalSessionFactoryBean fc(DataSource b) {
        val a = new LocalSessionFactoryBean();
        a.setDataSource(b);
        a.setPackagesToScan(this.getClass().getPackageName());

        val c = new Properties();
        c.setProperty("hibernate.dialect",
            "org.hibernate.dialect.PostgreSQL92Dialect");
        c.setProperty("hibernate.show_sql", "true");
        c.setProperty("hibernate.hbm2ddl.auto", "update");
        a.setHibernateProperties(c);
        return a;
    }

    @Bean
    public PlatformTransactionManager pt(LocalSessionFactoryBean a) {
        return new HibernateTransactionManager(
            Objects.requireNonNull(a.getObject()));
    }
}
