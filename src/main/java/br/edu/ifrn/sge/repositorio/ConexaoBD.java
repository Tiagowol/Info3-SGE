package br.edu.ifrn.sge.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    // URL, usuário e senha do seu banco MySQL local
    private static final String URL = "jdbc:mysql://localhost:3306/info3_sge";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin"; 

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}