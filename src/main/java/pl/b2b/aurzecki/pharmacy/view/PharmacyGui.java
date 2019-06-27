package pl.b2b.aurzecki.pharmacy.view;

import pl.b2b.aurzecki.pharmacy.table.ShowMainDatabase;
import pl.b2b.aurzecki.pharmacy.service.AddDatabaseToMedicine;
import pl.b2b.aurzecki.pharmacy.service.MainTable;

import javax.swing.*;
import java.awt.*;

public class PharmacyGui extends JFrame {

    private JLabel lWelcome;
    private JButton bDatabaseAddress;
    private JButton bMainDatabase;
    private JButton bExit;
    private JButton bDeleteMain;
    private AddDatabaseToMedicine addDatabaseToMedicine = new AddDatabaseToMedicine();
    private MainTable mainTable = new MainTable();
    private DatabaseConnectionWindow databaseConnectionWindow;
    private static final Font font = new Font("Monospaced", Font.BOLD, 12);
    private static final Font fontLabel = new Font("Monospaced", Font.BOLD, 16);


    public PharmacyGui() {
        setSize(1280, 720);
        setTitle("Pharmacy database application");
        setLayout(null);


        lWelcome = new JLabel("Pharmacy database Application");
        lWelcome.setFont(fontLabel);
        lWelcome.setBounds(460, 50, 300, 50);
        add(lWelcome);


        bMainDatabase = new JButton("Show main database");
        bMainDatabase.setFont(font);
        bMainDatabase.setBounds(100, 250, 285, 60);
        add(bMainDatabase);
        bMainDatabase.addActionListener(e -> {
            try {
                ShowMainDatabase.getGui();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });


        bDeleteMain = new JButton("Clear Main Database");
        bDeleteMain.setFont(font);
        bDeleteMain.setBounds(10, 600, 285, 40);
        add(bDeleteMain);
        bDeleteMain.addActionListener(e -> {
            mainTable.deleteAllFromMainDatabase();
        });


        bDatabaseAddress = new JButton("Add new Database");
        bDatabaseAddress.setFont(font);
        bDatabaseAddress.setBounds(400, 250, 285, 60);
        add(bDatabaseAddress);
        bDatabaseAddress.addActionListener(e -> {
            if (databaseConnectionWindow == null) {
                databaseConnectionWindow = new DatabaseConnectionWindow(this);
                this.setVisible(false);
            }
            databaseConnectionWindow.setVisible(true);
        });

        bExit = new JButton("Exit Application");
        bExit.setFont(font);
        bExit.setBounds(700, 250, 285, 60);
        add(bExit);
        bExit.addActionListener(e -> {
            System.exit(0);
        });


        // window closing event :
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setVisible(true);
    }
}


