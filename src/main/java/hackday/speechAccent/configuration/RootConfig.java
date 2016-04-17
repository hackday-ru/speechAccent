package hackday.speechAccent.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by nicaraguanec on 06.02.2016.
 */
@Configuration
@ComponentScan(basePackages = {"hackday.speechAccent"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
        })
@PropertySource({"classpath:database.properties", "classpath:application.properties"})
public class RootConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("database.url"));
        config.setDriverClassName(env.getProperty("database.driverName"));
        config.setUsername(env.getProperty("database.usename"));
        config.setPassword(env.getProperty("database.password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }
}
