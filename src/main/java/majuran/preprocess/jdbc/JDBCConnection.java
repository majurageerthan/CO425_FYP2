package majuran.preprocess.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class JDBCConnection {

    private static final String GENDER_TABLE_NAME = "gender";
    private static final String GRADES_TABLE_NAME = "end_grades";
    private static final String LOG_DATA_TABLE_NAME = "mdl_log";
    private static final String STUDENT_CODE_COLUMN_NAME = "std_code";
    private static final String STUDENTS_ONLY_TEMP_TABLE_NAME = "tempStudentsOnly";

    private final HashMap<Integer, Integer> mod_resource_course_module_viewed = new HashMap<>();
    private final HashMap<Integer, String> user_profile_viewed = new HashMap<>();
    private final HashMap<Integer, String> mod_forum_course_module_viewed = new HashMap<>();
    private final HashMap<Integer, String> discussion_viewed = new HashMap<>();
    private final HashMap<Integer, String> event_course_viewed = new HashMap<>();
    private final HashMap<Integer, String> assessable_uploaded = new HashMap<>();
    private final HashMap<Integer, String> feedback_course_module_viewed = new HashMap<>();
    private final HashMap<Integer, String> feedback_response_submitted = new HashMap<>();
    private final HashMap<Integer, String> post_created = new HashMap<>();

    public void initConnection() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("user");
        dataSource.setPassword("password");
        dataSource.setDatabaseName("original_data_3_0_e14_for_testing");

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement();) {
            createStudentsOnlyTempTableByEliminatingOthersData(stmt);
            initModResourceCourseModuleViewedMapFromTempTable(stmt);
         /*   initUserProfileViewdMapFromTempTable(stmt);
            initModForumCourseModuleViewedMapFromTempTable(stmt);
            initDiscussionViewdMapFromTempTable(stmt);
            initEventCourseViewedMapFromTempTable(stmt);
            initAssessableUploadedMapFromTempTable(stmt);
            initFeedbackCourseModuleViewedMapFromTempTable(stmt);
            initFeedbackResponseSubmittedMapFromTempTable(stmt);
            initPostCreatedMapFromTempTable(stmt);*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createStudentsOnlyTempTableByEliminatingOthersData(Statement stmt) throws SQLException {
        String CREATE_TABLE_SQL = "create temporary table " + STUDENTS_ONLY_TEMP_TABLE_NAME +
                " select " + LOG_DATA_TABLE_NAME + ".* from " + LOG_DATA_TABLE_NAME +
                " inner join " + GRADES_TABLE_NAME +
                " on " + LOG_DATA_TABLE_NAME + "." + STUDENT_CODE_COLUMN_NAME +
                " = " + GRADES_TABLE_NAME + "." + STUDENT_CODE_COLUMN_NAME;
        System.out.println(CREATE_TABLE_SQL);
        stmt.executeUpdate(CREATE_TABLE_SQL);
//        printAllResults(stmt);
    }

    private void initModResourceCourseModuleViewedMapFromTempTable(Statement stmt) throws SQLException {
//        \mod_resource\event\course_module_viewed
        String sql = "select std_code , count(std_code) as count_std_code " +
                "from " + STUDENTS_ONLY_TEMP_TABLE_NAME + " " +
                "where eventname = '\\\\mod_resource\\\\event\\\\course_module_viewed' " +
                "group by std_code order by count_std_code";
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMap(rs, mod_resource_course_module_viewed);
        printAllFromMap(mod_resource_course_module_viewed);
    }

    private void saveResultsToHashMap(ResultSet rs, HashMap<Integer, Integer> hashMap) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("std_code");
            int count = rs.getInt("count_std_code");
            System.out.println(id + ": " + count);
            hashMap.put(id, count);
        }
    }

    private void printAllFromMap(HashMap<Integer, Integer> hashMap) {
        for (int key : hashMap.keySet()) {
            System.out.println(key + ": " + hashMap.get(key));
        }
    }

    private void printAllResults(Statement stmt) throws SQLException {

        /*select std_code, count(std_code) as hit_rate_for_course_page
        from original_data.e15_mdl_logstore_2017
        where target = 'course'
        and action = 'viewed'
        group by std_code
        order by hit_rate_for_course_page;*/


        String x = "select distinct std_code , count(std_code) as count_discussion_viewed from mdl_log " +
                "where eventname = '\\\\mod_forum\\\\event\\\\discussion_viewed' " +
                "group by std_code order by count_discussion_viewed";

        String s = "select distinct std_code from " + STUDENTS_ONLY_TEMP_TABLE_NAME +
                " where eventname = '\\mod_forum\\event\\discussion_viewed'";
        System.out.println(x);
        ResultSet rs = stmt.executeQuery(x);
        int i = 1;
        while (rs.next()) {
            int id = rs.getInt("std_code");
            int count = rs.getInt("count_discussion_viewed");
            System.out.println(i++ + " " + id + " " + count);
        }
    }


}
