package br.edu.ifrn.sge.repositorio;

import br.edu.ifrn.sge.modelo.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepositorio {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula, idade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public List<Aluno> buscarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.nome = rs.getString("nome");
                aluno.matricula = rs.getString("matricula");
                aluno.idade = rs.getInt("idade");
                lista.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao buscar alunos: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(String matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.executeUpdate();
            System.out.println("[Sucesso] Aluno com matrícula " + matricula + " excluído!");
        } catch (SQLException e) {
            System.out.println("[Erro] Falha ao excluir aluno: " + e.getMessage());
        }
    }
}