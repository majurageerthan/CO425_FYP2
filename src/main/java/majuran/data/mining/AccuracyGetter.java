package majuran.data.mining;

import majuran.preprocess.MainPreprocess;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class AccuracyGetter {

    public static void printAccuracy() {
        try {
//            MainPreprocess.start();
            Instances train = getInstancesFromArff("merged.arff");
            Instances test = getInstancesFromArff("e16.arff");

            System.out.println(useKNNAndGetAccuracy(train, test));
            System.out.println(useJ48AndGetAccuracy(train, test));
            System.out.println(useLogisticAndGetAccuracy(train, test));
            System.out.println(useNaiveBayesAndGetAccuracy(train, test));
            System.out.println(useSVMAndGetAccuracy(train, test));
            System.out.println(useMLPAndGetAccuracy(train, test));

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

    private static String useKNNAndGetAccuracy(Instances train, Instances test) throws Exception {
        return getAccuracy(new IBk(), train, test, "\nK-nearest neighbours classifier\nResults\n======\n");
    }

    private static String useJ48AndGetAccuracy(Instances train, Instances test) throws Exception {
        return getAccuracy(new J48(), train, test, "\nJ48 classifier (C4.5 decision trees)\nResults\n======\n");
    }

    private static String useLogisticAndGetAccuracy(Instances train, Instances test) throws Exception {
        return getAccuracy(new Logistic(), train, test, "\nLogistic regression\nResults\n======\n");
    }

    private static String useNaiveBayesAndGetAccuracy(Instances train, Instances test) throws Exception {
        return getAccuracy(new NaiveBayes(), train, test, "\nNaive Bayes\nResults\n======\n");
    }

    private static String useSVMAndGetAccuracy(Instances train, Instances test) throws Exception {
        return getAccuracy(new SMO(), train, test, "\nSupport vector machines\nResults\n======\n");
    }

    private static String useMLPAndGetAccuracy(Instances train, Instances test) throws Exception {
        return getAccuracy(new MultilayerPerceptron(), train, test, "\nMultilayer Perceptron\nResults\n======\n");
    }

    private static String getAccuracy(Classifier cls, Instances train, Instances test, String title) throws Exception {
        // train classifier
        cls.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        // evaluate classifier and print some statistics
        eval.evaluateModel(cls, test);
        return eval.toSummaryString(title, false);
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

