package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TurmaRepositorio {

    public void salvar(Turma turma) {
        String sql = "INSERT INTO turma (nome, codigo) VALUES (?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, turma.nome);
            stmt.setString(2, turma.codigo);

            stmt.executeUpdate();
            System.out.println("[Sucesso] Turma " + turma.nome + " salva no banco de dados!");

        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao salvar a turma no banco: " + e.getMessage());
        }
    }
}