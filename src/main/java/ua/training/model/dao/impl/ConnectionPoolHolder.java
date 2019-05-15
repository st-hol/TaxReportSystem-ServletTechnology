package ua.training.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);


    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    Properties properties = new Properties();
                    String propFileName = "db.properties";

                    try(InputStream inputStream = Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(propFileName)) {

                        if (inputStream != null) {
                            properties.load(inputStream);
                        } else {
                            logger.fatal("Unable to open file:" + propFileName );
                            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                        }

                        //properties.load(new FileInputStream(
                        //      "E:\\Java\\epam courses\\IntroductoryCampaign\\src\\main\\resources\\db.properties"));


                        Class.forName(properties.getProperty("db.connection.driver"));

                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(properties.getProperty("db.connection.url"));
                        ds.setUsername(properties.getProperty("db.connection.username"));
                        ds.setPassword(properties.getProperty("db.connection.password"));

                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);

                        dataSource = ds;

                    } catch (IOException | ClassNotFoundException e) {
                        logger.fatal("Caught exception:", e);
                        e.printStackTrace();
                    }
                }
            }
        }

        return dataSource;
    }

}
