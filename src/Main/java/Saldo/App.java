package Saldo;

import Util.Email;
import Util.InteraLog;
import Util.SendEmail;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import javax.mail.Session;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

public class App {

    private static final String URL = "http://2captcha.com/res.php?action=getbalance&key=146414b118dceefcf9bd7f89c0eae8d9";

    public static void main (String[] args)throws IOException, SQLException{

        App.start(false);
    }

    public static void start(boolean flag) throws IOException, SQLException {

        flag = InteraLog.verificaLog(flag); // Verificação de LOG

        if (flag) {
            System.out.println("CICLO ENCERRADO");
        } else {
            Email conexaoEmail = new Email();
            SendEmail corpoEmail = new SendEmail();
            Session sessao = conexaoEmail.logar();

            WebClient browser = new WebClient();

            HtmlPage pagina = browser.getPage(URL);

            String saldo = pagina.asText();

            double value = Double.parseDouble(saldo);

            Locale locale = new Locale("en", "US");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            System.out.println(currencyFormatter.format(value));

            String saldoAtual = currencyFormatter.format(value);

            corpoEmail.enviaEmail(sessao, saldoAtual, value);

        }
    }
}
