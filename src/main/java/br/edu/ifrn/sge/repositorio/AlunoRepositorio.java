package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.repositorio.ConexaoBD; // Importando a conexão explicitamente
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoRepositorio {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula, idade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Acessando os dados diretamente para evitar erro de falta de métodos
            stmt.setString(1, aluno.nome);
            stmt.setString(2, aluno.matricula);
            stmt.setInt(3, aluno.idade);

            stmt.executeUpdate();
            System.out.println("SUCESSO: Aluno " + aluno.nome + " salvo no banco de dados!");

        } catch (SQLException e) {
            System.out.println("ERRO: Não foi possível salvar o aluno no banco.");
            e.printStackTrace();
        }
    }
}