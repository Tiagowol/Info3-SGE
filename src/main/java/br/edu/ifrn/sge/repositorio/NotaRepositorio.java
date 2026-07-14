package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}