package majuran.data.mining;

import majuran.preprocess.GlobalNames;
import majuran.preprocess.MainPreprocess;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.util.Random;

public class ModelEvaluation {

    private Instances train;
    private Instances test;

    private Evaluation randomForestEvaluation;
    private Evaluation kNNEvaluation;
    private Evaluation j48Evaluation;
    private Evaluation logisticEvaluation;
    private Evaluation naiveBayesEvaluation;
    private Evaluation sVMEvaluation;
    private Evaluation mLPEvaluation;

    public ModelEvaluation() {
        initInstances();
    }

    private void initInstances() {
        train = getInstancesFromArff(GlobalNames.mergedArffName + ".arff");
        test = getInstancesFromArff("e16.arff");
    }


    public void evaluateUsing10FoldCrossValidation() throws Exception {
        initEvaluationFor10FoldCrossValidation();
        printClassifierResults();
    }

    public void evaluateUsingTestAndTrain() throws Exception {
        initEvaluationForAllClassifier(train, test);
        printClassifierResults();
    }

    public void evaluvateUsingPercentageSplit() throws Exception {
        train.randomize(new java.util.Random(0));
        int trainSize = (int) Math.round(train.numInstances() * 0.8);
        int testSize = train.numInstances() - trainSize;
        Instances newTrain = new Instances(train, 0, trainSize);
        Instances newTest = new Instances(train, trainSize, testSize);
        initEvaluationForAllClassifier(newTrain, newTest);
        printClassifierResults();
    }

    private void initEvaluationFor10FoldCrossValidation() throws Exception {
        randomForestEvaluation = getEvaluationFor10CrossFold(new RandomForest());
        kNNEvaluation = getEvaluationFor10CrossFold(new IBk());
        j48Evaluation = getEvaluationFor10CrossFold(new J48());
        logisticEvaluation = getEvaluationFor10CrossFold(new Logistic());
        naiveBayesEvaluation = getEvaluationFor10CrossFold(new NaiveBayes());
        sVMEvaluation = getEvaluationFor10CrossFold(new SMO());
        mLPEvaluation = getEvaluationFor10CrossFold(new MultilayerPerceptron());
    }

    private void initEvaluationForAllClassifier(Instances train, Instances test) throws Exception {
        randomForestEvaluation = useRandomForestAndGetEvaluation(train, test);
        kNNEvaluation = useKNNAndGetEvaluation(train, test);
        j48Evaluation = useJ48AndGetEvaluation(train, test);
        logisticEvaluation = useLogisticAndGetEvaluation(train, test);
        naiveBayesEvaluation = useNaiveBayesAndGetEvaluation(train, test);
        sVMEvaluation = useSVMAndGetEvaluation(train, test);
        mLPEvaluation = useMLPAndGetEvaluation(train, test);
    }

    private void printClassifierResults() throws Exception {
        printClassifierResults(randomForestEvaluation, "\n************ Random Forest classifier ************\nResults\n======\n");
        printClassifierResults(kNNEvaluation, "\n************ K-nearest neighbours classifier ************\nResults\n======\n");
        printClassifierResults(j48Evaluation, "\n************ J48 classifier (C4.5 decision trees) ************\nResults\n======\n");
        printClassifierResults(logisticEvaluation, "\n************ Logistic regression ************\nResults\n======\n");
        printClassifierResults(naiveBayesEvaluation, "\n************ Naive Bayes ************\nResults\n======\n");
        printClassifierResults(sVMEvaluation, "\n************ Support vector machines ************\nResults\n======\n");
        printClassifierResults(mLPEvaluation, "\n************ Multilayer Perceptron ************\nResults\n======\n");
    }

    private Instances getInstancesFromArff(String arffPath) {
        try {
            String finalPath = MainPreprocess.finalArffFilePath + arffPath;
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(finalPath);
            Instances instances = source.getDataSet();
            if (instances.classIndex() == -1)
                instances.setClassIndex(instances.numAttributes() - 1);
            return instances;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error occured");
        }

    }

    private Evaluation useRandomForestAndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new RandomForest(), train, test);
    }

    private Evaluation useKNNAndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new IBk(), train, test);
    }

    private Evaluation useJ48AndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new J48(), train, test);
    }

    private Evaluation useLogisticAndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new Logistic(), train, test);
    }

    private Evaluation useNaiveBayesAndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new NaiveBayes(), train, test);
    }

    private Evaluation useSVMAndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new SMO(), train, test);
    }

    private Evaluation useMLPAndGetEvaluation(Instances train, Instances test) throws Exception {
        return getEvaluationUsingTestAndTrain(new MultilayerPerceptron(), train, test);
    }


    private Evaluation getEvaluationUsingTestAndTrain(Classifier cls, Instances train, Instances test) throws Exception {
        // train classifier
        cls.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(cls, test);
        return eval;
    }


    private Evaluation getEvaluationFor10CrossFold(Classifier cls) throws Exception {
        // train classifier
//        Classifier c1 = new NaiveBayes();
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(cls, train, 3, new Random(1));
        return eval;
    }

    public void printClassifierResults(Evaluation eval, String title) throws Exception {
        // Print the result à la Weka explorer:
        System.out.println("==========================================================");
        String strSummary = eval.toSummaryString(title, false);
        System.out.println(strSummary);

        // Print per class results
        String resPerClass = eval.toClassDetailsString();
        System.out.println(resPerClass);

        // Get the confusion matrix
        String cMatrix = eval.toMatrixString();
        System.out.println(cMatrix);

        System.out.println();
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

