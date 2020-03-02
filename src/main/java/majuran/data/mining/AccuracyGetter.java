package majuran.data.mining;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class AccuracyGetter {

    public static void printAccuracy(){
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("/some/where/data.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
