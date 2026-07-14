package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Atividade> buscarTodos() {
        List<Atividade> lista = new ArrayList<>();
        String sql = "SELECT * FROM atividade";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Atividade atividade = new Atividade();
                atividade.nome = rs.getString("nome");
                atividade.tipo = rs.getString("tipo");
                lista.add(atividade);
            }
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao buscar atividades: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(String nome) {
        String sql = "DELETE FROM atividade WHERE nome = ?";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
            System.out.println("[Sucesso] Atividade '" + nome + "' excluída!");
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao excluir atividade: " + e.getMessage());
        }
    }
}