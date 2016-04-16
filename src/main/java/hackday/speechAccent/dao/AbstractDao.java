package hackday.speechAccent.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by nicaraguanec on 07.02.2016.
 */
@Component
public abstract class AbstractDao extends JdbcDaoSupport {

    protected static final Logger LOGGER = Logger.getLogger("Dao logger");

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        setDataSource(dataSource);
    }
}
