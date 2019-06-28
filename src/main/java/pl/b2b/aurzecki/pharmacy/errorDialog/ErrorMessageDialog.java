package pl.b2b.aurzecki.pharmacy.errorDialog;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ErrorMessageDialog {


    public void columnMatchingError() {
        JOptionPane.showMessageDialog(null, "Columns data types don't match, please try again", "Error", ERROR_MESSAGE);
    }

    public void connectionError() {
        JOptionPane.showMessageDialog(null, "Connection error, please try again", "Error", ERROR_MESSAGE);
    }

    public void wrongDatabaseDetails() {
        JOptionPane.showMessageDialog(null, "Wrong database details, please try again", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void wrongExcelPath() {
        JOptionPane.showMessageDialog(null, "Wrong file path, please try again", "Error", ERROR_MESSAGE);
    }


}
