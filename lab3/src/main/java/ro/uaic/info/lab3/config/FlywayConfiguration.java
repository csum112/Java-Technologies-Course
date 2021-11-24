package ro.uaic.info.lab3.config;

import com.googlecode.flyway.core.Flyway;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@Slf4j
@WebListener
@ApplicationScoped
public class FlywayConfiguration implements ServletContextListener {
    private final static String DATASOURCE_CONTEXT = "java:jboss/datasources/localPostgresDS";

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.debug("Running flyway migration");
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setInitOnMigrate(true);
        flyway.migrate();
        log.debug("Finished running flyway migration");
    }


    private static DataSource getDataSource() throws NamingException {
        return (DataSource) new InitialContext().lookup(DATASOURCE_CONTEXT);
    }
}
