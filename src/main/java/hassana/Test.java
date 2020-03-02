package hassana;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;


public class test {

    public static void get_accuracy() {
        try {
            //String arffPath;
            Instances train = getInstancesFromArff("Testing/e14.arff");
            Instances test = getInstancesFromArff("Testing/e15.arff");

            //j48 classifier
            Classifier cls = new J48();
            cls.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(cls, test);
            //boolean title;
            System.out.println(eval.toSummaryString("\nResults\n=========\n", false));

            //Multilayer perceptron
            MultilayerPerceptron m = new MultilayerPerceptron();
            m.buildClassifier(train);

            Evaluation ev = new Evaluation(train);
            ev.evaluateModel(m,test);

            System.out.println(ev.toSummaryString("\nResults\n=========\n", false));

            //Naivebayes classifier
            NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
            nb.buildClassifier(train);

            Evaluation ev_nb = new Evaluation(train);
            ev_nb.evaluateModel(nb,test);
            System.out.println(ev.toSummaryString("\nResults\n=========\n", false));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Instances getInstancesFromArff(String arffPath) throws Exception{
        String finalPath= arffPath;

        //finalPath =
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(finalPath);
        Instances instances = source.getDataSet();
        if(instances.classIndex() == -1)
            instances.setClassIndex(instances.numAttributes() - 1);
        return instances;
    }


}
