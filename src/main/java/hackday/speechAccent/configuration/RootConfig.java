package hackday.speechAccent.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
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
@PropertySource({"classpath:database.properties"})
public class RootConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(env.getProperty("database.driverName"));
        comboPooledDataSource.setJdbcUrl(env.getProperty("database.url"));
        comboPooledDataSource.setUser(env.getProperty("database.usename"));
        comboPooledDataSource.setPassword(env.getProperty("database.password"));
        return comboPooledDataSource;
    }
}
