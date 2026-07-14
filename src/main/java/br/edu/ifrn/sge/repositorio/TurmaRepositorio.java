package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Turma> buscarTodos() {
        List<Turma> lista = new ArrayList<>();
        String sql = "SELECT * FROM turma";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Turma turma = new Turma();
                turma.nome = rs.getString("nome");
                turma.codigo = rs.getString("codigo");
                lista.add(turma);
            }
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao buscar turmas: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(String codigo) {
        String sql = "DELETE FROM turma WHERE codigo = ?";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            System.out.println("[Sucesso] Turma com código " + codigo + " excluída!");
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao excluir turma: " + e.getMessage());
        }
    }
}