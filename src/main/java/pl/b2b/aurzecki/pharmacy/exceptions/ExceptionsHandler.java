package pl.b2b.aurzecki.pharmacy.exceptions;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ExceptionsHandler {

    public boolean isDatabaseConnectionFormFilled(final String dbUrl, final String login) {
        boolean result = true;
        if ((dbUrl == null) || dbUrl.equals("") || (login == null) || login.equals("")) {
            result = false;
        }
        return result;
    }

    public void isExcelFilePathValid(String path) {
        if (path.equals("") || !path.contains(".xls") || !path.contains(".xlsx")) {
            JOptionPane.showMessageDialog(null, "Wrong file path, please try again", "Error", ERROR_MESSAGE);
        }
    }

    public void columnMatchingError() {
        JOptionPane.showMessageDialog(null, "Columns data types don't match, please try again", "Error", ERROR_MESSAGE);
    }

    public void driverError() {
        JOptionPane.showMessageDialog(null, "Ups something went wrong, please try again and check if You choose correct driver", "Error", ERROR_MESSAGE);
    }

    public void connectionError() {
        JOptionPane.showMessageDialog(null, "Connection error, please try again", "Error", ERROR_MESSAGE);
    }


}
