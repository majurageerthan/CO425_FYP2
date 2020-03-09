package majuran.data.mining;

import org.junit.jupiter.api.Test;

class ModelEvaluationTest {

    @Test
    void printAccuracy() {
        try {
//            new ModelEvaluation().evaluateUsing10FoldCrossValidation();
            new ModelEvaluation().evaluateUsingTestAndTrain();
//            new ModelEvaluation().evaluvateUsingPercentageSplit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}