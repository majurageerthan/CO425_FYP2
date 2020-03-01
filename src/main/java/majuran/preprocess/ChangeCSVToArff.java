package majuran.preprocess;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;

public class ChangeCSVToArff {

    public static void createArrfFrom(String batch) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File(MainPreprocess.finalFilePath + batch + ".csv"));
            Instances data = loader.getDataSet();

            // save ARFF
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File(MainPreprocess.finalArffFilePath + batch + ".arff"));
//        saver.setDestination(new File(args[1]));
            saver.writeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
