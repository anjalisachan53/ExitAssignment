package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelUtils {
    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);
    private Sheet sheet;

    public ExcelUtils(String excelPath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(excelPath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                logger.error("Sheet {} does not exist in the Excel file: {}", sheetName, excelPath);
                System.out.println("Sheet " + sheetName + " does not exist in the Excel file: " + excelPath); // Added print statement
                throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in the Excel file: " + excelPath);
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", excelPath, e);
            System.out.println("Error reading Excel file: " + excelPath); // Added print statement
            throw new RuntimeException("Error reading Excel file: " + excelPath, e);
        }
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        String cellData = formatter.formatCellValue(cell);
        System.out.println("Data read from cell [" + rowNum + "," + colNum + "]: " + cellData); // Added print statement
        return cellData;
    }
}
