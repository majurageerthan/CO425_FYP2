package majuran.preprocess.jdbc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDBCConnectionTest {

    @Test
    void initConnection() {
        JDBCConnection jdbcConnection = new JDBCConnection();
        jdbcConnection.initConnection();
    }
}