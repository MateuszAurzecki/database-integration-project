package pl.b2b.aurzecki.pharmacy.exceptions;

import pl.b2b.aurzecki.pharmacy.errorDialog.ErrorMessageDialog;

public class ConnectionExceptions extends BaseException {
    private static final String DRIVER = "There was a problem connecting to a database, please check database details";

    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();

    public ConnectionExceptions() {

        super(ConnectionExceptions.DRIVER);
        errorMessageDialog.connectionError();
    }
}
