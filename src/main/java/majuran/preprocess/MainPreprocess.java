package majuran.preprocess;

import com.mysql.cj.jdbc.MysqlDataSource;
import majuran.preprocess.model.BatchSQLDetail;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class MainPreprocess {

    private static final String E14_LOG_SQL_TABLE_NAME = "original_data.e14_mdl_logstore_2016";
    private static final String E15_LOG_SQL_TABLE_NAME = "original_data2_0.e15_logs_2017";
    private static final String E16_LOG_SQL_TABLE_NAME = "original_data2_0.e16_logs_2018";

    private static final String E14_FINAL_GRADES_TABLE_NAME = "original_data.e14_grades";
    private static final String E15_FINAL_GRADES_TABLE_NAME = "original_data.e15_grades";
    private static final String E16_FINAL_GRADES_TABLE_NAME = "original_data2_0.e16_grades";

    private static final String E14_GENDER_TABLE_NAME = "original_data.e14_gender";
    private static final String E15_GENDER_TABLE_NAME = "original_data2_0.e15_gender";
    private static final String E16_GENDER_TABLE_NAME = "original_data2_0.e16_gender";

    public static final String finalFilePath = "preprocessed_files/";
    public static final String finalArffFilePath = finalFilePath + "arff/";


    private static MysqlDataSource dataSource = new MysqlDataSource();


    public static void start() {
        dataSource.setUser("user");
        dataSource.setPassword("password");
        dataSource.setDatabaseName("original_data_3_0_e14_for_testing");

        createDirsIfNotExists();

        ArrayList<BatchSQLDetail> batchSQLDetails = getDummyDetails();
        for (BatchSQLDetail batchSQLDetail : batchSQLDetails) {
            initJDBCConnectionAndStartPreprocess(batchSQLDetail);
            ChangeCSVToArff.createArrfFrom(batchSQLDetail.getBatchName());
            batchSQLDetail = null;
        }

        ArrayList<String> trainCSVPathList = new ArrayList<>();
        trainCSVPathList.add(finalFilePath + "e14.csv");
        trainCSVPathList.add(finalFilePath + "e15.csv");
//        trainCSVPathList.add(finalFilePath + "e16.csv");

        try {
            MergeTogetherHelper.mergeManyCSVFilesIntoOneCSV(trainCSVPathList);
            ChangeCSVToArff.createArrfFrom("merged");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void createDirsIfNotExists() {
        File csvFile = new File(finalFilePath);
        File arffFile = new File(finalArffFilePath);
        csvFile.mkdirs();
        arffFile.mkdirs();

    }

    private static void initJDBCConnectionAndStartPreprocess(BatchSQLDetail batchSQLDetail) {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            PreprocessByBatchSQLDetail preprocessByBatchSQLDetail = new PreprocessByBatchSQLDetail(batchSQLDetail, stmt);
            preprocessByBatchSQLDetail.setShouldConsiderUptoMidData(true);
            preprocessByBatchSQLDetail.setOmitStdCode(true);
            preprocessByBatchSQLDetail.startPreprocess();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<BatchSQLDetail> getDummyDetails() {
        BatchSQLDetail e14BatchSQLDetail = new BatchSQLDetail();
        e14BatchSQLDetail.setBatchName("e14");
        e14BatchSQLDetail.setLogTableName(E14_LOG_SQL_TABLE_NAME);
        e14BatchSQLDetail.setFinalGradesTableName(E14_FINAL_GRADES_TABLE_NAME);
        e14BatchSQLDetail.setGenderTableName(E14_GENDER_TABLE_NAME);

        BatchSQLDetail e15BatchSQLDetail = new BatchSQLDetail();
        e15BatchSQLDetail.setBatchName("e15");
        e15BatchSQLDetail.setLogTableName(E15_LOG_SQL_TABLE_NAME);
        e15BatchSQLDetail.setFinalGradesTableName(E15_FINAL_GRADES_TABLE_NAME);
        e15BatchSQLDetail.setGenderTableName(E15_GENDER_TABLE_NAME);

        BatchSQLDetail e16BatchSQLDetail = new BatchSQLDetail();
        e16BatchSQLDetail.setBatchName("e16");
        e16BatchSQLDetail.setLogTableName(E16_LOG_SQL_TABLE_NAME);
        e16BatchSQLDetail.setFinalGradesTableName(E16_FINAL_GRADES_TABLE_NAME);
        e16BatchSQLDetail.setGenderTableName(E16_GENDER_TABLE_NAME);

        ArrayList<BatchSQLDetail> batchSQLDetails = new ArrayList<>();
        batchSQLDetails.add(e14BatchSQLDetail);
        batchSQLDetails.add(e15BatchSQLDetail);
        batchSQLDetails.add(e16BatchSQLDetail);
        return batchSQLDetails;
    }

}
