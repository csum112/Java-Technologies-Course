package ro.uaic.info.lab3.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ConnectionFactory {
    private final DataSource dataSource;

    @Inject
    public ConnectionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Produces
    public Connection createConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
