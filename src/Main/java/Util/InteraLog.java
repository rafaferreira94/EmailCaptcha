package Util;

import Entity.Querys;
import Factory.Conexao;
import Saldo.App;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Saldo.App.start;

public class InteraLog {

    public static boolean verificaLog(boolean flag) throws SQLException {

        Connection conexao = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        conexao = Conexao.processosConexao();
        st = conexao.createStatement();
        ps = conexao.prepareStatement(Querys.verificaLog);
        /*try {
            rs = ps.executeQuery();
            while(rs.next()) {
                while (rs.getString(1)!= null) {
                    System.out.println("EMAIL JÁ ENVIADO - "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    flag = true;
                    break;
                }

            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }*/

        try {
            ps.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            rs = ps.executeQuery();
            while(rs.next()) {
                while (rs.getString(1)!= null) {
                    System.out.println("EMAIL JÁ ENVIADO - "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    flag = true;
                    break;
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void insertLog(final String saldo) {
        Connection conexao = null;
        Statement st = null;
        //PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = Conexao.processosConexao();
            st = conexao.createStatement();
            PreparedStatement ps = conexao.prepareStatement(Querys.insertLog);

            try {
                ps.setString(1, "Email já enviado");
                ps.setString(2, saldo);
                ps.execute();
                ps.close();

                System.out.println("LOG INSERIDO");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
