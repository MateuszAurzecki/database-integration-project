package pl.b2b.aurzecki.pharmacy.validator;

public class Validator {

    public boolean isDatabaseConnectionFormFilled(final String dbUrl, final String login) {
        boolean result = true;
        if ((dbUrl == null) || dbUrl.equals("") || (login == null) || login.equals("")) {
            result = false;
        }
        return result;
    }

    public boolean isExcelFilePathValid(String path) {
        boolean result = false;
        if (path.equals("") || !path.contains(".xls") || !path.contains(".xlsx")) {
            result = true;
        }
        return result;
    }
}
