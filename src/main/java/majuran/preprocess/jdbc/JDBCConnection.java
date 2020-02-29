package majuran.preprocess.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

    private static final String studentsOnlyTempTableName = "";

    public static void main(String[] args) {
        initConnection();
    }

    public static void initConnection() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("user");
        dataSource.setPassword("password");
        dataSource.setDatabaseName("original_data");

        String CREATE_TABLE_SQL = "create temporary table temp1 " +
                "select * " +
                "from e14_mdl_logstore_2016 " +
                "where timecreated < 1474363001";

//        dataSource.setServerName("myDBHost.example.org");

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(CREATE_TABLE_SQL);
            ResultSet rs = stmt.executeQuery("select * from temp1");
            int i = 1;
            while (rs.next()) {
                int id = rs.getInt("id");
                System.out.println(i++ + " " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void getStudentsOnlyDataByEliminatingOthersData(){

    }

    public static void viewTable(Connection con, String dbName)
            throws SQLException {

        Statement stmt = null;
        String query =
                "select COF_NAME, SUP_ID, PRICE, " +
                        "SALES, TOTAL " +
                        "from " + dbName + ".COFFEES";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t" + supplierID +
                        "\t" + price + "\t" + sales +
                        "\t" + total);
            }
        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
