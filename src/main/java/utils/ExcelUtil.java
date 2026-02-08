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
 * Utility class for handling Excel file operations used by the test suite.
 *
 * <p>This class provides small helpers to read test data from XLSX files and
 * convert it into Java-friendly structures. The implementation is defensive
 * against common Excel quirks (blank cells, numeric cells, missing rows) by
 * using Apache POI's DataFormatter to extract cell values as strings.</p>
 *
 * Notes:
 * - Methods return empty lists on recoverable errors and null for severe
 *   failures where callers previously expected null (preserves existing
 *   behaviour in the codebase).
 * - The helper {@link #getCellStringValue(Cell)} centralizes cell extraction
 *   logic and keeps the rest of the code readable.
 */
public class ExcelUtil {

	// Reusable formatter that converts any cell type to a human-friendly string
	// (handles numbers, dates, booleans, formulas, and strings).
	private static final DataFormatter DATA_FORMATTER = new DataFormatter();

	/**
	 * Reads data from an Excel file and converts it to a list of maps. Each map
	 * represents a row where keys are column headers and values are cell contents.
	 *
	 * @param filePath the path to the Excel file to be read
	 * @return List of maps containing the Excel data, where each map represents a
	 *         row with column headers as keys and cell values as values. Returns an
	 *         empty list if an error occurs during reading.
	 * @throws RuntimeException if the file cannot be read or is not a valid Excel
	 *                          file
	 */
	public static List<Map<String, String>> readExcelData(String filePath) {
		List<Map<String, String>> testData = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			// Defensive: ensure sheet index 1 exists
			if (workbook.getNumberOfSheets() < 2) {
				return testData; // return empty list if requested sheet missing
			}

			// Get the second sheet from the workbook
			Sheet sheet = workbook.getSheetAt(1);

			// Get the header row (first row) and validate
			Row headerRow = sheet.getRow(0);
			if (headerRow == null) {
				return testData; // no header => no data
			}

			// Process each row starting from index 1 (skipping header)
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row currentRow = sheet.getRow(i);
				if (currentRow == null) {
					continue; // skip empty rows
				}
				Map<String, String> data = new HashMap<>();

				// Process each cell in the current row. Use header cell count to
				// determine the columns to read so we don't rely on lastCellNum of
				// the current row which may be shorter.
				int headerCells = headerRow.getLastCellNum();
				for (int j = 0; j < headerCells; j++) {
					String header = getCellStringValue(headerRow.getCell(j));
					String value = getCellStringValue(currentRow.getCell(j));
					data.put(header, value);
				}
				testData.add(data);
			}
		} catch (Exception e) {
			// Log the exception and return empty list (preserve previous behavior)
			e.printStackTrace();
		}
		return testData;
	}

	/**
	 * Reads the first column of the named sheet and returns the values as a
	 * list of trimmed strings. This is useful when tests supply a list of
	 * 'search' inputs in the first column of a sheet.
	 *
	 * @param filePath the Excel file path
	 * @param sheetName the sheet name to read
	 * @return ordered list of values from column A (first column) starting at row 2
	 */
	public static List<String> getSearchItems(String filePath, String sheetName) {
		List<String> items = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				return items; // sheet missing -> empty list
			}
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null && row.getCell(0) != null) {
					String cellValue = getCellStringValue(row.getCell(0));
					if (!cellValue.isEmpty()) {
						items.add(cellValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	/**
	 * Loads Excel data for use in a data-provider style test. This method reads
	 * the first two columns of the given sheet and returns them as a 2D object
	 * array where each row corresponds to an Excel row (skipping header).
	 *
	 * @param path the Excel file path
	 * @param sheetName the name of the sheet to read
	 * @return Object[][] where each inner array has two elements corresponding
	 *         to the first two columns. Returns null on failure (preserves
	 *         previous behavior).
	 */
	public static Object[][] getExcelData(String path, String sheetName) {
		try (FileInputStream fis = new FileInputStream(path); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				return null;
			}
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows <= 1) {
				return new Object[0][0]; // no data rows
			}
			int cols = sheet.getRow(0).getLastCellNum();

			Object[][] data = new Object[rows - 1][2];

			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					data[i - 1][0] = "";
					data[i - 1][1] = "";
					continue;
				}
				data[i - 1][0] = getCellStringValue(row.getCell(0));
				data[i - 1][1] = getCellStringValue(row.getCell(1));
			}

			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Defensive helper to extract a string value from a POI Cell. Handles null
	 * cells and converts numbers, booleans, dates, formulas, and strings into
	 * a trimmed String value using DataFormatter.
	 *
	 * @param cell the POI Cell instance (may be null)
	 * @return trimmed cell value as String, or empty string if cell is null
	 */
	private static String getCellStringValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		try {
			return DATA_FORMATTER.formatCellValue(cell).trim();
		} catch (Exception e) {
			// Fallback: attempt to read as string to avoid breaking calling code
			try {
				return cell.getStringCellValue().trim();
			} catch (Exception ex) {
				return "";
			}
		}
	}

}