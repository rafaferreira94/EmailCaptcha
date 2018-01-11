package Entity;

public class Querys {

    public static final String verificaLog = "SELECT * FROM EMAIL_CAPTCHA WHERE DATA_LOG >= ?";

    public static final String insertLog = "INSERT INTO EMAIL_CAPTCHA (STATUS_LOG, DATA_LOG, SALDO) VALUES (?, GETDATE(), ?);";

}
