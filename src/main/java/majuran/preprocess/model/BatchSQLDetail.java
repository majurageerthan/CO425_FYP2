package majuran.preprocess.model;

public class BatchSQLDetail {
    private String batchName;
    private String logTableName;
    private String finalGradesTableName;
    private String genderTableName;

    public BatchSQLDetail() {
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getLogTableName() {
        return logTableName;
    }

    public void setLogTableName(String logTableName) {
        this.logTableName = logTableName;
    }

    public String getFinalGradesTableName() {
        return finalGradesTableName;
    }

    public void setFinalGradesTableName(String finalGradesTableName) {
        this.finalGradesTableName = finalGradesTableName;
    }

    public String getGenderTableName() {
        return genderTableName;
    }

    public void setGenderTableName(String genderTableName) {
        this.genderTableName = genderTableName;
    }
}
