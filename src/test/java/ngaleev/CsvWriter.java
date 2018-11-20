package ngaleev;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.testng.Assert.fail;

public class CsvWriter {
    private static final String filePath = "target\\files\\popular_goods.csv";

    public static boolean createFile(List<String> itemsToWrite) {
        BufferedWriter writer = null;
        CSVPrinter csvPrinter = null;

        try {
            File outputFile = new File(filePath);
            outputFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(filePath));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name"));
            for(String itemName: itemsToWrite){
                csvPrinter.printRecord(itemName);
            }
            csvPrinter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            fail();
            return false;
        }
    }
}
