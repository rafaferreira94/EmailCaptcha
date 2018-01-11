package Util;

import Saldo.App;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SendEmail {
    private static final String from = "bpa@finchsolucoes.com.br";

    public static void enviaEmail (final Session sessao, final String saldo, final double value){

        try{
            MimeMessage mensagem = new MimeMessage(sessao);
            Multipart multipart = new MimeMultipart();

            mensagem.setFrom(new InternetAddress(from));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("nicholasrohwedder@finchsolucoes.com.br"));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("valterleite@finchsolucoes.com.br"));
            mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("monitoramentorobo@finchsolucoes.com.br"));
           // mensagem.addRecipient (Message.RecipientType.TO, new InternetAddress("rafaelmartin@finchsolucoes.com.br"));

            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM");

            String assunto = "Saldo Captcha " + dataAtuaDB();

            mensagem.setSubject(assunto, "utf-8");

            BodyPart corpo = new MimeBodyPart();

            StringBuffer corpoEmail = new StringBuffer ("<html><head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" + "<style>" + "body {"
                    + "font-family: 'Arial'; font-size: 14px;" + "}" + "</style>" + "</head>" + "</body>"
                    + "<p> Prezados boa tarde, <b></b><br>"
                    + "<p>Saldo do captcha do dia: "+ dataAtuaDB() + " está em: US" + saldo + " <b></b><br><br>"
                    +"<b>Email enviado automaticamente</b>");

            if (value < 1000) {
                corpoEmail.append("<br><br><font color='red'><b>SALDO ABAIXO DO LIMITE, POR FAVOR REALIZE A RECARGA</b></font><br>");
            }

            corpo.setContent( corpoEmail.toString(), "text/html; charset=utf-8" );

            multipart.addBodyPart(corpo);

            mensagem.setContent(multipart);
            Transport.send(mensagem);

            System.out.println("EMAIL ENVIADO");
//          AQUI VOCÊ VAI INSERIR O LOG APÓS ENVIAR O EMAIL
            InteraLog.insertLog(saldo);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String dataAtuaDB() { //FORMATA A DATA DE HOJE
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return hoje.format(formatador);
    }
}
