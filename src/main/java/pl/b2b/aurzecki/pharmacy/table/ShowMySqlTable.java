package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.model.MySqlDatabaseModel;
import pl.b2b.aurzecki.pharmacy.service.MySqlCreator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowMySqlTable extends JPanel {

    private MySqlCreator mySqlCreator = new MySqlCreator();
    private List<String> columnNames;
    private List<MySqlDatabaseModel> mySqlList;

    private ShowMySqlTable(final String dbUrl, final String dbLogin, final String dbPass) {
        super(new GridLayout(1, 0));

        //getting data from database
        mySqlList = mySqlCreator.getSqlDatabase(dbUrl, dbLogin, dbPass);


        //getting column names for table
        columnNames = mySqlCreator.mySqlTableColumnNames(dbUrl, dbLogin, dbPass);


        //inserting data to columns
        Object[][] database = new Object[mySqlList.size()][3];
        for (int i = 0; i < mySqlList.size(); i++) {
            database[i][0] = mySqlList.get(i).getIdent();
            database[i][1] = mySqlList.get(i).getNazwa();
            database[i][2] = mySqlList.get(i).getMinisterstwo();
        }


        final JTable table = new JTable(database, columnNames.toArray());
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private static void createAndShowGUI(final String dbUrl, final String dbLogin, final String dbPass) {
        //Create and set up the window.
        JFrame frame = new JFrame("Show MySql Table");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        ShowMySqlTable newContentPane = new ShowMySqlTable(dbUrl, dbLogin, dbPass);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void getGui(final String dbUrl, final String dbLogin, final String dbPass) {
        createAndShowGUI(dbUrl, dbLogin, dbPass);
    }
}
