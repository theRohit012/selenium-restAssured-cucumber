package CommonUtilityFunctions;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    /**
     * @method Read a Excel File
     * @param excelPath
     * @param sheetName
     * @return
     */
    public List<Map<String, String>> readExcel(String excelPath, String sheetName) {
        Workbook book = null;
        List<Map<String, String>> data = new ArrayList<>();
        FileInputStream file = null;

        try {
            file = new FileInputStream(excelPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = book.getSheet(sheetName);
        Row header = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Map<String, String> row = new HashMap<>();
            Row currentRow = sheet.getRow(i);
            for (int j = 0; j < header.getLastCellNum(); j++) {
                Cell currentCell = currentRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String cellValue = currentCell.getStringCellValue().trim();
                row.put(header.getCell(j).toString(), cellValue);
            }
            data.add(row);
        }
        return data;
    }

    /**
     * @method Write into a Excel file
     * @param excelFilePath
     * @param sheetName
     * @param data
     */
    public void writeToExcel(String excelFilePath, String sheetName, List<Map<String,String>> data){
        Workbook book = new XSSFWorkbook();
        Sheet sheet = null;

        if(book.getSheet(sheetName) == null){
            sheet = book.createSheet(sheetName);
        } else {
            sheet = book.getSheet(sheetName);
            clearSheetData(sheet);
        }

        Row headerRow = sheet.createRow(0);
        int colInx = 0;
        for(String key : data.get(0).keySet()){
            Cell cell = headerRow.createCell(colInx++);
            cell.setCellValue(key);
        }

        int rowInx = 1;
        for(Map<String,String> map : data){
            Row row = sheet.createRow(rowInx++);
            colInx = 0;
            for(String key : map.keySet()){
                Cell cell = row.createCell(colInx++);
                cell.setCellValue(map.get(key));
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(excelFilePath);
            book.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @method Clear a excel sheet
     * @param sheet
     */
    private void clearSheetData(Sheet sheet){
        int lastRow = sheet.getLastRowNum();
        for(int i = lastRow; i>=0; i--){
            Row row = sheet.getRow(i);
            if(row != null){
                sheet.removeRow(row);
            }
        }
    }
}
