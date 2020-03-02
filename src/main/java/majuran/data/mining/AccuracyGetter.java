package majuran.data.mining;

import majuran.preprocess.MainPreprocess;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class AccuracyGetter {

    public static void printAccuracy() {
        try {

            Instances train = getInstancesFromArff("co421/e14.arff");
            Instances test = getInstancesFromArff("co421/e15.arff");
            // train classifier
            Classifier cls = new J48();
            cls.buildClassifier(train);
            // evaluate classifier and print some statistics
            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(cls, test);
            System.out.println(eval.toSummaryString("\nResults\n======\n", false));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Instances getInstancesFromArff(String arffPath) throws Exception {
        String finalPath = MainPreprocess.finalArffFilePath + arffPath;
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(finalPath);
        Instances instances = source.getDataSet();
        if (instances.classIndex() == -1)
            instances.setClassIndex(instances.numAttributes() - 1);
        return instances;

    }
}

 /*Classifiers for categorical prediction:
         weka.classifiers.IBk: k-nearest neighbour learner
         weka.classifiers.j48.J48: C4.5 decision trees
         weka.classifiers.j48.PART: rule learner
         weka.classifiers.NaiveBayes: naive Bayes with/without kernels
         weka.classifiers.OneR: Holte's OneR
         weka.classifiers.KernelDensity: kernel density classifier
         weka.classifiers.SMO: support vector machines
         weka.classifiers.Logistic: logistic regression
         weka.classifiers.AdaBoostM1: AdaBoost
         weka.classifiers.LogitBoost: logit boost
         weka.classifiers.DecisionStump: decision stumps (for boosting)
         b) Classifiers for numeric prediction:
         weka.classifiers.LinearRegression: linear regression
         weka.classifiers.m5.M5Prime: model trees
         weka.classifiers.IBk: k-nearest neighbour learner
         weka.classifiers.LWR: locally weighted regression
         weka.classifiers.RegressionByDiscretization: uses categorical classifiers*/

