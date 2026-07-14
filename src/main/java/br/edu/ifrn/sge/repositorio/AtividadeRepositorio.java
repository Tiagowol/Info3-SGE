package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtividadeRepositorio {

    public void salvar(Atividade atividade) {
        String sql = "INSERT INTO atividade (nome, tipo) VALUES (?, ?)";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, atividade.nome);
            stmt.setString(2, atividade.tipo);

            stmt.executeUpdate();
            System.out.println("[Sucesso] Atividade '" + atividade.nome + "' salva no banco de dados!");
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao salvar a atividade no banco: " + e.getMessage());
        }
    }
}