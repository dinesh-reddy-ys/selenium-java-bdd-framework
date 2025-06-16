package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for handling Excel file operations.
 */
public class ExcelUtil {
    
    /**
     * Reads data from an Excel file and converts it to a list of maps.
     * Each map represents a row where keys are column headers and values are cell contents.
     *
     * @param filePath the path to the Excel file to be read
     * @return List of maps containing the Excel data, where each map represents a row
     *         with column headers as keys and cell values as values.
     *         Returns an empty list if an error occurs during reading.
     * @throws RuntimeException if the file cannot be read or is not a valid Excel file
     */
    public static List<Map<String, String>> readExcelData(String filePath) {
        List<Map<String, String>> testData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
            
            // Get the header row (first row)
            Row headerRow = sheet.getRow(0);
            
            // Process each row starting from index 1 (skipping header)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> data = new HashMap<>();
                
                // Process each cell in the current row
                for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                    String header = headerRow.getCell(j).getStringCellValue();
                    String value = currentRow.getCell(j).getStringCellValue();
                    data.put(header, value);
                }
                testData.add(data);
            }
        } catch (Exception e) {
            // Log the exception and return empty list
            e.printStackTrace();
        }
        return testData;
    }

    public static List<String> getSearchItems(String filePath, String sheetName) {
        List<String> items = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    items.add(row.getCell(0).toString().trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}