package majuran.preprocess.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ExtraWork {
    public void createEventNameMap(Statement stmt) throws SQLException {
        HashMap<String, Integer> e14EventNameMap = new HashMap<>();
        HashMap<String, Integer> e15EventNameMap = new HashMap<>();
        HashMap<String, Integer> e16EventNameMap = new HashMap<>();

        String e16Sql = "select distinct eventname, count(eventname) as count_eventname " +
                "from original_data2_0.e16_logs_2018 " +
                "group by eventname " +
                "order by count_eventname";
        ResultSet rs = stmt.executeQuery(e16Sql);

        while (rs.next()) {
            String eventName = rs.getString("eventname");
            int count_eventname = rs.getInt("count_eventname");
            e16EventNameMap.put(eventName, count_eventname);
        }

        String e15Sql = "select distinct eventname, count(eventname) as count_eventname " +
                "from original_data2_0.e15_logs_2017 " +
                "group by eventname " +
                "order by count_eventname";
        ResultSet rs2 = stmt.executeQuery(e15Sql);

        while (rs2.next()) {
            String eventName = rs2.getString("eventname");
            int count_eventname = rs2.getInt("count_eventname");
            e15EventNameMap.put(eventName, count_eventname);
        }

        String e14Sql = "select distinct eventname, count(eventname) as count_eventname " +
                "from mdl_log " +
                "group by eventname " +
                "order by count_eventname";
        ResultSet rs3 = stmt.executeQuery(e14Sql);

        while (rs3.next()) {
            String eventName = rs3.getString("eventname");
            int count_eventname = rs3.getInt("count_eventname");
            e14EventNameMap.put(eventName, count_eventname);
        }

        System.out.println("eventName");
        for (String key : e14EventNameMap.keySet()) {
            if (e15EventNameMap.containsKey(key) && e16EventNameMap.containsKey(key)) {
                System.out.println(key);
            }
        }

        System.out.println("count_e14");
        for (String key : e14EventNameMap.keySet()) {
            if (e15EventNameMap.containsKey(key) && e16EventNameMap.containsKey(key)) {
                System.out.println(e14EventNameMap.get(key));
            }
        }

        System.out.println();
        System.out.println("count_e15");
        for (String key : e14EventNameMap.keySet()) {
            if (e15EventNameMap.containsKey(key) && e16EventNameMap.containsKey(key)) {
                System.out.println(e15EventNameMap.get(key));
            }
        }
        System.out.println();
        System.out.println("count_e16");
        for (String key : e14EventNameMap.keySet()) {
            if (e15EventNameMap.containsKey(key) && e16EventNameMap.containsKey(key)) {
                System.out.println(e16EventNameMap.get(key));
            }
        }


    }

    public void viewTable(Connection con, String dbName)
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
