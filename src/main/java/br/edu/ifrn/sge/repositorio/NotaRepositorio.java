package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaRepositorio {

    public void salvar(Nota nota) {
        String sql = "INSERT INTO nota (valor, disciplina) VALUES (?, ?)";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDouble(1, nota.valor);
            stmt.setString(2, nota.disciplina);

            stmt.executeUpdate();
            System.out.println("[Sucesso] Nota de valor " + nota.valor + " em " + nota.disciplina + " salva no banco de dados!");
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao salvar a nota no banco: " + e.getMessage());
        }
    }

    public List<Nota> buscarTodos() {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM nota";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Nota nota = new Nota();
                nota.valor = rs.getDouble("valor");
                nota.disciplina = rs.getString("disciplina");
                lista.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao buscar notas: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(String disciplina, double valor) {
        String sql = "DELETE FROM nota WHERE disciplina = ? AND valor = ?";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, disciplina);
            stmt.setDouble(2, valor);
            stmt.executeUpdate();
            System.out.println("[Sucesso] Nota de " + valor + " em " + disciplina + " excluída!");
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao excluir nota: " + e.getMessage());
        }
    }
}