package pl.b2b.aurzecki.pharmacy.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.b2b.aurzecki.pharmacy.exceptions.ExceptionsHandler;
import pl.b2b.aurzecki.pharmacy.model.ExcelDatabaseModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelCreator {

    public ExceptionsHandler exceptionsHandler = new ExceptionsHandler();


    //function mapping excel file table to ExcelDatabaseModel objects and return it as a list of objects
    public List<ExcelDatabaseModel> getDatabase(String filePath) {

        List<ExcelDatabaseModel> result = new ArrayList<>();
        File file = new File(filePath);
        try (
                FileInputStream fip = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(fip)) {
            DataFormatter dataFormatter = new DataFormatter();

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                ExcelDatabaseModel excelDatabaseModel = new ExcelDatabaseModel();
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        excelDatabaseModel.setLp(Long.valueOf(dataFormatter.formatCellValue(cell)));
                    } else if (cell.getColumnIndex() == 1) {
                        excelDatabaseModel.setNazwa(dataFormatter.formatCellValue(cell));
                    } else if (cell.getColumnIndex() == 2) {
                        excelDatabaseModel.setId_w_ministerstwie(Long.valueOf(dataFormatter.formatCellValue(cell)));

                    }
                }
                result.add(excelDatabaseModel);
            }
        } catch (IOException e) {
            exceptionsHandler.isExcelFilePathValid(filePath);
        }
        return result;
    }

    //function returns list of string with names of columns in table
    public List<String> excelTableColumnNames(String filePath) throws IOException {
        List<String> result = new ArrayList<>();

        File file = new File(filePath);
        try (
                FileInputStream fip = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(fip)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            for (Cell cell : row) {
                result.add(cell.getStringCellValue());
            }
        }
        return result;
    }
}
