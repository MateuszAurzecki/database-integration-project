package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.creator.MySqlCreator;
import pl.b2b.aurzecki.pharmacy.domain.SqlDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowMySqlTable extends JPanel {


    private ShowMySqlTable(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        super(new GridLayout(1, 0));

        String[] columnNames = {"ident",
                "nazwa",
                "ministerstwo"};

        MySqlCreator mySqlCreator = new MySqlCreator();
        List<SqlDatabase> mySqlList = mySqlCreator.getSqlDatabase(dbUrl, dbLogin, dbPass);


        Object[][] database = new Object[mySqlList.size()][3];
        for (int i = 0; i < mySqlList.size(); i++) {
            database[i][0] = mySqlList.get(i).getIdent();
            database[i][1] = mySqlList.get(i).getNazwa();
            database[i][2] = mySqlList.get(i).getMinisterstwo();
        }


        final JTable table = new JTable(database, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private static void createAndShowGUI(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
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

    public static void getGui(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        createAndShowGUI(dbUrl, dbLogin, dbPass);
    }
}
