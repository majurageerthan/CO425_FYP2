package majuran.preprocess;

public class GradeIntegrateHelper {
    public static String getIntegratedGrades(String originalGrade) {
        String integratedGrade;
        switch (originalGrade) {
            case "A-":
            case "A":
            case "A+":
                integratedGrade = "A";
                break;

            case "B-":
            case "B":
            case "B+":
                integratedGrade = "B";
                break;

            case "C-":
            case "C":
            case "C+":
                integratedGrade = "C";
                break;

            case "D":
            case "D+":
                integratedGrade = "D";
                break;

            case "E":
                integratedGrade = "E";
                break;

            case "NA":
                integratedGrade = "NA";
                break;

            default:
                throw new IllegalArgumentException("Invalid grade");
        }
//        System.out.println(integratedGrade);
        return integratedGrade;
    }
}
