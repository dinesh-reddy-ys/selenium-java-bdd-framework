package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public static List<Map<String, String>> readExcelData(String filePath) {
        List<Map<String, String>> testData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // Reads the first sheet
            Row headerRow = sheet.getRow(0); // Assumes first row contains headers

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> data = new HashMap<>();
                for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                    String header = headerRow.getCell(j).getStringCellValue();
                    String value = currentRow.getCell(j).getStringCellValue();
                    data.put(header, value);
                }
                testData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }
}