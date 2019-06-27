package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.service.H2Creator;
import pl.b2b.aurzecki.pharmacy.model.H2Database;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ShowH2Table extends JPanel {

    private H2Creator h2Creator = new H2Creator();
    private List<String> columnNames;
    private List<H2Database> h2Database;


    private ShowH2Table(final String dbUrl, final String dbLogin, final String dbPass) throws ClassNotFoundException {
        super(new GridLayout(1, 0));

        //getting column names for table
        try {
            columnNames = h2Creator.h2TableColumnNames(dbUrl, dbLogin, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //getting data from database
        h2Database = h2Creator.getH2Database(dbUrl, dbLogin, dbPass);

        //inserting data to columns
        Object[][] database = new Object[h2Database.size()][3];
        for (int i = 0; i < h2Database.size(); i++) {
            database[i][0] = h2Database.get(i).getIdentyfikator();
            database[i][1] = h2Database.get(i).getNazwaLeku();
            database[i][2] = h2Database.get(i).getMin();
        }


        final JTable table = new JTable(database, columnNames.toArray());
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }


    private static void createAndShowGUI(final String dbUrl, final String dbLogin, final String dbPass) throws ClassNotFoundException {
        //Create and set up the window.
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        ShowH2Table h2Table = new ShowH2Table(dbUrl, dbLogin, dbPass);
        h2Table.setOpaque(true); //content panes must be opaque
        dialog.setContentPane(h2Table);

        //Display the window.
        dialog.pack();
        dialog.setVisible(true);
        dialog.setModal(true);

    }

    public static void getGui(final String dbUrl, final String dbLogin, final String dbPass) throws ClassNotFoundException {
        createAndShowGUI(dbUrl, dbLogin, dbPass);
    }
}

