package framework.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private static final String filePath = System.getProperty("csvFileToSave");

    public static boolean createFile(List<String> itemsToWrite) {
        BufferedWriter writer;
        CSVPrinter csvPrinter;

        try {
            File outputFile = new File(filePath);
            if (!outputFile.exists()) {
                if (!outputFile.createNewFile()) {
                    return false;
                }
            }
            writer = new BufferedWriter(new FileWriter(filePath));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name"));
            for (String itemName : itemsToWrite) {
                csvPrinter.printRecord(itemName);
            }
            csvPrinter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
