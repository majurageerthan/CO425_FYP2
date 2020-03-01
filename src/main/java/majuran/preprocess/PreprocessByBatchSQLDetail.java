package majuran.preprocess;

import com.opencsv.CSVWriter;
import majuran.preprocess.model.BatchSQLDetail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PreprocessByBatchSQLDetail {

    private static final String STUDENT_CODE_COLUMN_NAME = "std_code";
    private static final String GRADES_COLUMN_NAME = "grade";
    private static final String GENDER_COLUMN_NAME = "gender";

    private static final String STUDENTS_ONLY_TEMP_TABLE_NAME = "tempStudentsOnly";
    private final HashMap<String, String> mod_resource_course_module_viewed = new HashMap<>();
    private final HashMap<String, String> user_profile_viewed = new HashMap<>();
    private final HashMap<String, String> mod_forum_course_module_viewed = new HashMap<>();
    private final HashMap<String, String> discussion_viewed = new HashMap<>();
    private final HashMap<String, String> event_course_viewed = new HashMap<>();
    private final HashMap<String, String> assessable_uploaded = new HashMap<>();
    private final HashMap<String, String> feedback_course_module_viewed = new HashMap<>();
    private final HashMap<String, String> feedback_response_submitted = new HashMap<>();
    private final HashMap<String, String> post_created = new HashMap<>();
    private final HashMap<String, String> gender = new HashMap<>();
    private final HashMap<String, String> final_grades = new HashMap<>();


    private List<HashMap<String, String>> eventMapArrayList = Arrays.asList(
            mod_resource_course_module_viewed,
            user_profile_viewed,
            mod_forum_course_module_viewed,
            discussion_viewed,
            event_course_viewed,
            assessable_uploaded,
            feedback_course_module_viewed,
            feedback_response_submitted,
            post_created,
            gender,
            final_grades
    );

    private String[] titleArray = {STUDENT_CODE_COLUMN_NAME,
            "mod_resource_course_module_viewed",
            "user_profile_viewed",
            "mod_forum_course_module_viewed",
            "discussion_viewed",
            "event_course_viewed",
            "assessable_uploaded",
            "feedback_course_module_viewed",
            "feedback_response_submitted",
            "post_created",
            "gender",
            "final_grades"
    };

    private String BATCH_NAME;
    private String GENDER_TABLE_NAME;// = "gender";
    private String GRADES_TABLE_NAME;//= "end_grades";
    private String LOG_DATA_TABLE_NAME;// = "mdl_log";
    private BatchSQLDetail batchSQLDetail;
    private Statement stmt;
    private boolean shouldConsiderUptoMidData;

    public PreprocessByBatchSQLDetail(BatchSQLDetail batchSQLDetail, Statement stmt) {
        this.batchSQLDetail = batchSQLDetail;
        this.stmt = stmt;
        initInstances();
    }

    private void initInstances() {
        LOG_DATA_TABLE_NAME = batchSQLDetail.getLogTableName();
        GRADES_TABLE_NAME = batchSQLDetail.getFinalGradesTableName();
        GENDER_TABLE_NAME = batchSQLDetail.getGenderTableName();
        BATCH_NAME = batchSQLDetail.getBatchName();
    }

    public void setShouldConsiderUptoMidData(boolean shouldConsiderUptoMidData) {
        this.shouldConsiderUptoMidData = shouldConsiderUptoMidData;
    }

    public void startPreprocess() throws SQLException, IOException {

       /* MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("user");
        dataSource.setPassword("password");
        dataSource.setDatabaseName("original_data_3_0_e14_for_testing");*/

        createStudentsOnlyTempTableByEliminatingOthersData();
        initModResourceCourseModuleViewedMapFromTempTable();
        initUserProfileViewedMapFromTempTable();
        initModForumCourseModuleViewedMapFromTempTable();
        initDiscussionViewedMapFromTempTable();
        initEventCourseViewedMapFromTempTable();
        initAssessableUploadedMapFromTempTable();
        initFeedbackCourseModuleViewedMapFromTempTable();
        initFeedbackResponseSubmittedMapFromTempTable();
        initPostCreatedMapFromTempTable();
        initGender();
        initFinalGrades();
        saveResultsToCSV();

    }

    private void initFinalGrades() throws SQLException {
        String sql = "select " + STUDENT_CODE_COLUMN_NAME + " , " + GRADES_COLUMN_NAME +
                " from " + GRADES_TABLE_NAME;
        ResultSet rs = stmt.executeQuery(sql);
        getStringResultsAndSaveResultsToHashMap(rs, final_grades, GRADES_COLUMN_NAME);
    }

    private void initGender() throws SQLException {
        String sql = "select " + STUDENT_CODE_COLUMN_NAME + " , " + GENDER_COLUMN_NAME +
                " from " + GENDER_TABLE_NAME;
        ResultSet rs = stmt.executeQuery(sql);
        getStringResultsAndSaveResultsToHashMap(rs, gender, GENDER_COLUMN_NAME);
    }

    private void saveResultsToCSV() throws IOException {
        CSVWriter writer = new CSVWriter(
                new OutputStreamWriter(new FileOutputStream(MainPreprocess.finalFilePath + BATCH_NAME + ".csv"), StandardCharsets.UTF_8),
                ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
        );

        writer.writeNext(titleArray);


        ArrayList<String> countValues = new ArrayList<>();
        for (String key : mod_resource_course_module_viewed.keySet()) {
            countValues.add(key);

            for (HashMap<String, String> hashMap : eventMapArrayList) {
                countValues.add(hashMap.getOrDefault(key, "0"));
            }
            String[] strings = countValues.toArray(new String[0]);
            writer.writeNext(strings);
            countValues.clear();
        }

        writer.close();
    }

    private void createStudentsOnlyTempTableByEliminatingOthersData() throws SQLException {
        String CREATE_TABLE_SQL = "create temporary table " + STUDENTS_ONLY_TEMP_TABLE_NAME +
                " select " + LOG_DATA_TABLE_NAME + ".* from " + LOG_DATA_TABLE_NAME +
                " inner join " + GRADES_TABLE_NAME +
                " on " + LOG_DATA_TABLE_NAME + "." + STUDENT_CODE_COLUMN_NAME +
                " = " + GRADES_TABLE_NAME + "." + STUDENT_CODE_COLUMN_NAME;
        if (shouldConsiderUptoMidData) CREATE_TABLE_SQL += getMidPointQuery();
        System.out.println(CREATE_TABLE_SQL);
        stmt.executeUpdate(CREATE_TABLE_SQL);
//        printAllResults(stmt);
    }

    private String getMidPointQuery() {
//        ResultSet rs = stmt.executeQuery(sql);
        return " where timecreated < (((select max(timecreated)" +
                "from " + LOG_DATA_TABLE_NAME +
                " where objecttable = 'feedback_completed') + " +
                "(select min(timecreated) from " + LOG_DATA_TABLE_NAME + "))/2)";
    }

    //\mod_resource\event\course_module_viewed
    private void initModResourceCourseModuleViewedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_resource\\\\event\\\\course_module_viewed");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, mod_resource_course_module_viewed);
//        printAllFromMap(mod_resource_course_module_viewed);
    }

    ////\ core\ event\ user_profile_viewed
    private void initUserProfileViewedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\core\\\\event\\\\user_profile_viewed");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, user_profile_viewed);
    }

    //    \mod_forum\event\course_module_viewed
    private void initModForumCourseModuleViewedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_forum\\\\event\\\\course_module_viewed");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, mod_forum_course_module_viewed);
    }

    //    \mod_forum\event\discussion_viewed
    private void initDiscussionViewedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_forum\\\\event\\\\discussion_viewed");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, discussion_viewed);
    }

    //    \core\event\course_viewed
    private void initEventCourseViewedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\core\\\\event\\\\course_viewed");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, event_course_viewed);
    }

    //\mod_forum\event\assessable_uploaded
    private void initAssessableUploadedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_forum\\\\event\\\\assessable_uploaded");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, assessable_uploaded);
    }

    //    \mod_feedback\event\course_module_viewed
    private void initFeedbackCourseModuleViewedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_feedback\\\\event\\\\course_module_viewed");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, feedback_course_module_viewed);
    }

    //    \mod_feedback\event\response_submitted
    private void initFeedbackResponseSubmittedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_feedback\\\\event\\\\response_submitted");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, feedback_response_submitted);
    }

    //    \mod_forum\event\post_created
    private void initPostCreatedMapFromTempTable() throws SQLException {
        String sql = getCountEventSQL("\\\\mod_forum\\\\event\\\\post_created");
        ResultSet rs = stmt.executeQuery(sql);
        saveResultsToHashMapForEvents(rs, post_created);
    }

    private String getCountEventSQL(String eventName) {
        return "select " + STUDENT_CODE_COLUMN_NAME + " , count(" + STUDENT_CODE_COLUMN_NAME + ") as count_std_code " +
                "from " + STUDENTS_ONLY_TEMP_TABLE_NAME + " " +
                "where eventname = '" + eventName + "' " +
                "group by " + STUDENT_CODE_COLUMN_NAME + " order by count_std_code";
    }

    private void saveResultsToHashMapForEvents(ResultSet rs, HashMap<String, String> hashMap) throws SQLException {
        saveResultsToHashMap(rs, hashMap, "count_std_code");

       /* while (rs.next()) {
            int id = rs.getInt("std_code");
            int count = rs.getInt("count_std_code");
//            System.out.println(id + ": " + count);
            hashMap.put(String.valueOf(id), String.valueOf(count));
        }*/
    }

    private void saveResultsToHashMap(ResultSet rs, HashMap<String, String> hashMap, String secondColumnName) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt(STUDENT_CODE_COLUMN_NAME);
            int count = rs.getInt(secondColumnName);
//            System.out.println(id + ": " + count);
            hashMap.put(String.valueOf(id), String.valueOf(count));
        }
    }

    private void getStringResultsAndSaveResultsToHashMap(ResultSet rs, HashMap<String, String> hashMap, String secondColumnName) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt(STUDENT_CODE_COLUMN_NAME);
            String second = rs.getString(secondColumnName);
//            System.out.println(id + ": " + count);
            hashMap.put(String.valueOf(id), second);
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
