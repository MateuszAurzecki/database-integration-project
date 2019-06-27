package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.model.ExcelDatabaseModel;
import pl.b2b.aurzecki.pharmacy.service.ExcelCreator;
import pl.b2b.aurzecki.pharmacy.exceptions.ExceptionsHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ShowExcelTable extends JPanel {

    private ExcelCreator excelCreator = new ExcelCreator();
    private List<String> columnNames;
    private ExceptionsHandler exceptionsHandler = new ExceptionsHandler();

    public ShowExcelTable(final String path) {
        super(new GridLayout(1, 0));

        //getting column names for table
        try {
            columnNames = excelCreator.excelTableColumnNames(path);
        } catch (IOException e) {
            exceptionsHandler.isExcelFilePathValid(path);
        }

        //getting data from database
        List<ExcelDatabaseModel> excelDatabaseModel = excelCreator.getDatabase(path);

        //inserting data to columns
        Object[][] database = new Object[excelDatabaseModel.size()][3];
        for (int i = 0; i < excelDatabaseModel.size(); i++) {
            database[i][0] = excelDatabaseModel.get(i).getLp();
            database[i][1] = excelDatabaseModel.get(i).getNazwa();
            database[i][2] = excelDatabaseModel.get(i).getId_w_ministerstwie();
        }

        final JTable table = new JTable(database, columnNames.toArray());
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }


    private static void createAndShowGUI(final String path) {
        JFrame frame = new JFrame("ShowExcelTable");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ShowExcelTable newContentPane = new ShowExcelTable(path);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void getGui(final String path) {
        createAndShowGUI(path);
    }
}