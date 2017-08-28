package jp.co.future.uroborosql.springboot.demo.config;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.config.DefaultSqlConfig;
import jp.co.future.uroborosql.config.SqlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Spring configuration
 *
 * @author Kenichi Hoshi
 */
@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${petclinic.db-auto-init}")
    private Boolean dbAutoInit;

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/");
            container.addErrorPages(error404Page);
        });
    }

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        return ds;
    }

    @Bean
    public String initDatabase(DataSource dataSource) throws SQLException {
        if (dbAutoInit) {
            SqlConfig config = DefaultSqlConfig.getConfig(dataSource.getConnection());
            try (SqlAgent agent = config.createAgent()) {
                agent.required(() -> {
                    agent.update("setup/schema").count();
                    agent.update("setup/data").count();
                });
            }
            LOG.info("Complete database initialization.");
        } else {
            LOG.info("Skip database initialization.");
        }
        return null;
    }
}
