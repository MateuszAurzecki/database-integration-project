package pl.b2b.aurzecki.pharmacy.creator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.b2b.aurzecki.pharmacy.domain.ExcelDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelCreator {

    public List<ExcelDatabase> getDatabase(String filePath) throws IOException {
        List<ExcelDatabase> result = new ArrayList<>();

        File file = new File(filePath);
        FileInputStream fip = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fip);
        DataFormatter dataFormatter = new DataFormatter();

        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            ExcelDatabase excelDatabase = new ExcelDatabase();
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 0) {
                    excelDatabase.setLp(Long.valueOf(dataFormatter.formatCellValue(cell)));
                } else if (cell.getColumnIndex() == 1) {
                    excelDatabase.setNazwa(dataFormatter.formatCellValue(cell));
                } else if (cell.getColumnIndex() == 2) {
                    excelDatabase.setId_w_ministerstwie(Long.valueOf(dataFormatter.formatCellValue(cell)));

                }
            }
            result.add(excelDatabase);
        }
        return result;
    }

}
