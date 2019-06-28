package pl.b2b.aurzecki.pharmacy.exceptions;

import pl.b2b.aurzecki.pharmacy.errorDialog.ErrorMessageDialog;

public class FilePathExceptions extends BaseException {
    private static final String FILE_PATH = "File path not exist, please try again";

    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();

    public FilePathExceptions() {

        super(FilePathExceptions.FILE_PATH);
        errorMessageDialog.wrongExcelPath();
    }
}
