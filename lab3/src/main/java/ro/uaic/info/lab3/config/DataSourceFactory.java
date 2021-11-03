package ro.uaic.info.lab3.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class DataSourceFactory {
    private final static String DATA_SOURCE_NAME = "localhost";
    private final static String DATABASE_NAME = "postgres";
    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";

    @Produces
    public DataSource createDataSource() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName(DATA_SOURCE_NAME);
        source.setDatabaseName(DATABASE_NAME);
        source.setUser(USER);
        source.setPassword(PASSWORD);
        return source;
    }
}
