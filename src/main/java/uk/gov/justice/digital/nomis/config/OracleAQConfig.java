package uk.gov.justice.digital.nomis.config;

import oracle.jdbc.OracleConnection;
import oracle.jms.AQjmsFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.QueueConnectionFactory;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Configuration
@Profile("oracle")
public class OracleAQConfig {

//    @Bean
//    public QueueConnectionFactory aqConnectionFactory(@Qualifier("aqDataSource") DataSource aqDataSource) throws Exception {
//        return AQjmsFactory.getQueueConnectionFactory(aqDataSource);
//    }

    @Bean
    public QueueConnectionFactory aqConnectionFactory(JdbcTemplate jdbcTemplate) throws Exception {
        return AQjmsFactory.getQueueConnectionFactory(aqDataSourceOf(jdbcTemplate.getDataSource()));
    }

//    @Bean
    public DataSource aqDataSourceOf(DataSource dataSource) {

        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                final OracleConnection unwrap = dataSource.getConnection().unwrap(OracleConnection.class);
//                final boolean execute = unwrap.prepareStatement("alter session set current_schema =  API_OWNER").execute();
                return unwrap;
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return dataSource.getConnection(username, password).unwrap(OracleConnection.class);
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return dataSource.unwrap(iface);
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return dataSource.isWrapperFor(iface);
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                return dataSource.getLogWriter();
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {
                dataSource.setLogWriter(out);
            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return dataSource.getLoginTimeout();
            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {
                dataSource.setLoginTimeout(seconds);
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return dataSource.getParentLogger();
            }
        };
    }

//    @Bean
//    public JmsTemplate aqJmsTemplate(@Qualifier("aqConnectionFactory") QueueConnectionFactory aqConnectionFactory) {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(aqConnectionFactory);
////        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
//        return jmsTemplate;
//    }

    @Bean
    @Order
    public JmsListenerContainerFactory aqListenerContainerFactory(@Qualifier("aqConnectionFactory") QueueConnectionFactory aqConnectionFactory) {
        final DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(aqConnectionFactory);
        return defaultJmsListenerContainerFactory;
    }

}
