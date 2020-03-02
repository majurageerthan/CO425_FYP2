package majuran.preprocess;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MergeTogetherHelper {

    public static void mergeManyCSVFilesIntoOneCSV(List<String> trainCSVPathList) throws IOException {
        ArrayList<String[]> mergedLists = new ArrayList<>();
        boolean isHeaderAdded = false;

        for (String path : trainCSVPathList) {
            CSVReader reader = new CSVReader(new FileReader(path));
            List<String[]> myEntries = reader.readAll();

            if (isHeaderAdded) {
                myEntries.remove(0);
            } else {
                isHeaderAdded = true;
            }

            mergedLists.addAll(myEntries);
        }

        CSVWriter writer = new CSVWriter(
                new OutputStreamWriter(new FileOutputStream(MainPreprocess.finalFilePath + "merged.csv"), StandardCharsets.UTF_8),
                ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
        );

        for (String[] mergedList : mergedLists) {
            writer.writeNext(mergedList);
        }
        writer.close();

    }
}
