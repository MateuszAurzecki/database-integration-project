package pl.b2b.aurzecki.pharmacy.gui;

import pl.b2b.aurzecki.pharmacy.creator.H2Creator;
import pl.b2b.aurzecki.pharmacy.creator.MySqlCreator;
import pl.b2b.aurzecki.pharmacy.table.ShowExcelTable;
import pl.b2b.aurzecki.pharmacy.table.ShowH2Table;
import pl.b2b.aurzecki.pharmacy.table.ShowMainDatabase;
import pl.b2b.aurzecki.pharmacy.table.ShowMySqlTable;
import pl.b2b.aurzecki.pharmacy.utils.AddDatabaseToMedicine;
import pl.b2b.aurzecki.pharmacy.utils.MainTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PharmacyGui extends JFrame implements ActionListener {

    private JTextField tDriver;
    private JTextField tDbUrl;
    private JTextField tLogin;
    private JTextField tPassword;
    private JTextField tExcelFile;
    private JLabel lDriver;
    private JLabel lDbUrl;
    private JLabel lLogin;
    private JLabel lPassword;
    private JLabel lExcelUrl;
    private JLabel lWelcome;
    private JButton bConnect;
    private JButton bExcelConnect;
    private JButton bShowExcelDatabase;
    private JButton bShowH2Database;
    private JButton bShowMySqlDatabase;
    private JButton bMainDatabase;
    private JButton bAddH2ToMainDatabase;
    private JButton bAddExcelToMainDatabase;
    private JButton bAddMySqlToMainDatabase;
    private JButton bMergeAllDatabases;
    private JButton bCreateMainTable;
    private JButton bDeleteMain;
    private AddDatabaseToMedicine addDatabaseToMedicine = new AddDatabaseToMedicine();
    private MainTable mainTable = new MainTable();
    private IdWindow idWindow;
    private static final Font font = new Font("Monospaced", Font.BOLD, 12);
    private static final Font fontLabel = new Font("Monospaced", Font.BOLD, 16);


    public PharmacyGui() {
        setSize(1280, 720);
        setTitle("Pharmacy database application");
        setLayout(null);


        lWelcome = new JLabel("Pharmacy database Application");
        lWelcome.setFont(fontLabel);
        lWelcome.setBounds(460, 5, 300, 50);
        add(lWelcome);

        lDriver = new JLabel("Database Driver");
        lDriver.setFont(fontLabel);
        lDriver.setBounds(360, 50, 200, 40);
        add(lDriver);

        tDriver = new JTextField();
        tDriver.setBounds(560, 55, 200, 30);
        add(tDriver);

        lDbUrl = new JLabel("Database Url");
        lDbUrl.setFont(fontLabel);
        lDbUrl.setBounds(360, 90, 200, 40);
        add(lDbUrl);

        tDbUrl = new JTextField();
        tDbUrl.setBounds(560, 95, 200, 30);
        add(tDbUrl);


        lLogin = new JLabel("Login");
        lLogin.setFont(fontLabel);
        lLogin.setBounds(360, 130, 200, 40);
        add(lLogin);

        tLogin = new JTextField();
        tLogin.setBounds(560, 135, 200, 30);
        add(tLogin);

        lPassword = new JLabel("Password");
        lPassword.setFont(fontLabel);
        lPassword.setBounds(360, 170, 200, 40);
        add(lPassword);

        tPassword = new JTextField();
        tPassword.setBounds(560, 175, 200, 30);
        add(tPassword);

        lExcelUrl = new JLabel("Excel Path");
        lExcelUrl.setFont(fontLabel);
        lExcelUrl.setBounds(840, 50, 150, 40);
        add(lExcelUrl);

        tExcelFile = new JTextField();
        tExcelFile.setBounds(980, 55, 200, 30);
        add(tExcelFile);

        bExcelConnect = new JButton("Show Excel File");
        bExcelConnect.setFont(font);
        bExcelConnect.setBounds(910, 120, 200, 40);
        add(bExcelConnect);
        bExcelConnect.addActionListener(this);

        bConnect = new JButton("Connect");
        bConnect.setFont(font);
        bConnect.setBounds(460, 240, 200, 40);
        add(bConnect);
        bConnect.addActionListener(this);


        bShowExcelDatabase = new JButton("Excel database");
        bShowExcelDatabase.setFont(font);
        bShowExcelDatabase.setBounds(10, 400, 285, 40);
        add(bShowExcelDatabase);
        bShowExcelDatabase.addActionListener(e -> {
            try {
                ShowExcelTable.getGui("src/main/resources/ExcelDatabase.xlsx");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        bShowH2Database = new JButton("H2 database");
        bShowH2Database.setFont(font);
        bShowH2Database.setBounds(325, 400, 285, 40);
        add(bShowH2Database);
        bShowH2Database.addActionListener(e -> {
            try {
                ShowH2Table.getGui(H2Creator.DB_URL, H2Creator.USER, H2Creator.PASS);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        bShowMySqlDatabase = new JButton("MySql database");
        bShowMySqlDatabase.setFont(font);
        bShowMySqlDatabase.setBounds(640, 400, 285, 40);
        add(bShowMySqlDatabase);
        bShowMySqlDatabase.addActionListener(e -> {
            try {
                ShowMySqlTable.getGui(MySqlCreator.DB_URL, MySqlCreator.USER, MySqlCreator.PASS);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });


        bMainDatabase = new JButton("Main database");
        bMainDatabase.setFont(font);
        bMainDatabase.setBounds(955, 400, 285, 40);
        add(bMainDatabase);
        bMainDatabase.addActionListener(e -> {
            try {
                ShowMainDatabase.getGui();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        bAddExcelToMainDatabase = new JButton("Add Excel File To Database");
        bAddExcelToMainDatabase.setFont(font);
        bAddExcelToMainDatabase.setBounds(10, 500, 285, 40);
        add(bAddExcelToMainDatabase);
        bAddExcelToMainDatabase.addActionListener(e -> {
            try {
                addDatabaseToMedicine.addExcelToMedicine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        bAddH2ToMainDatabase = new JButton("Add H2 To Database");
        bAddH2ToMainDatabase.setFont(font);
        bAddH2ToMainDatabase.setBounds(325, 500, 285, 40);
        add(bAddH2ToMainDatabase);
        bAddH2ToMainDatabase.addActionListener(e -> {
            try {
                addDatabaseToMedicine.addH2ToMedicine();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });


        bAddMySqlToMainDatabase = new JButton("Add MySql To Database");
        bAddMySqlToMainDatabase.setFont(font);
        bAddMySqlToMainDatabase.setBounds(640, 500, 285, 40);
        add(bAddMySqlToMainDatabase);
        bAddMySqlToMainDatabase.addActionListener(e -> {
            try {
                addDatabaseToMedicine.addMySqlToMedicine();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        bMergeAllDatabases = new JButton("Merge All Databases");
        bMergeAllDatabases.setFont(font);
        bMergeAllDatabases.setBounds(955, 500, 285, 40);
        add(bMergeAllDatabases);
        bMergeAllDatabases.addActionListener(e -> {
            if (idWindow == null) {
                idWindow = new IdWindow(this);
            }
            idWindow.setVisible(true);
        });

        bDeleteMain = new JButton("Clear Main Database");
        bDeleteMain.setFont(font);
        bDeleteMain.setBounds(10, 600, 285, 40);
        add(bDeleteMain);
        bDeleteMain.addActionListener(e -> {
            mainTable.deleteAllFromMainDatabase();
        });


        bCreateMainTable = new JButton("Create Main Table");
        bCreateMainTable.setFont(font);
        bCreateMainTable.setBounds(325, 600, 285, 40);
        add(bCreateMainTable);
        bCreateMainTable.addActionListener(e -> {
            if (idWindow == null) {
                idWindow = new IdWindow(this);
            }
            idWindow.setVisible(true);
        });


        // window closing event :
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bConnect) {
            String dbDriver = tDriver.getText();
            String dbUrl = tDbUrl.getText();
            String dbLogin = tLogin.getText();
            String dbPass = tPassword.getText();
            if (dbDriver.equals("org.h2.Driver")) {
                try {
                    ShowH2Table.getGui(dbUrl, dbLogin, dbPass);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            } else if (dbDriver.equals("com.mysql.cj.jdbc.Driver")) {
                try {
                    ShowMySqlTable.getGui(dbUrl, dbLogin, dbPass);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }

        } else if (source == bExcelConnect) {
            String excelPath = tExcelFile.getText();
            try {
                ShowExcelTable.getGui(excelPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }
}


