package majuran.preprocess;

import java.util.HashMap;

public class GradeCounter {
    int aNo = 0;
    int bNo = 0;
    int cNo = 0;

    public void countGrade(HashMap<String, String> integrated_final_grades) {
        for (String grade : integrated_final_grades.values()) {
            countGrade(grade);
        }

        printGrades();
    }


    public void countGrade(String grade) {
        if ("A".equals(grade)) {
            aNo++;
        } else if ("B".equals(grade)) {
            bNo++;
        } else if ("C".equals(grade)) {
            cNo++;
        }
    }

    public void printGrades() {
        System.out.println("A: " + aNo + ", B: " + bNo + ", C: " + cNo);
    }
}
