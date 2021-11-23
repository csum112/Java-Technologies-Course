package ro.uaic.info.lab3.config;

import com.googlecode.flyway.core.Flyway;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@Slf4j
@WebListener
@ApplicationScoped
public class FlywayConfiguration implements ServletContextListener {
    @Inject
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Running flyway migration");
        System.out.println("Running migration");
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setInitOnMigrate(true);
        flyway.migrate();
    }
}
