package majuran;


import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Test {

    {
        try {
            DataSource source = new DataSource("/some/where/data.arff");
            Instances data = source.getDataSet();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
