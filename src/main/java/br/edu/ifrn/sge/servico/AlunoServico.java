package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.repositorio.AlunoRepositorio;
import java.util.List;

public class AlunoServico {

    private AlunoRepositorio repositorio = new AlunoRepositorio();

    public void cadastrarAluno(Aluno aluno) {
        // Validação 1: Nome obrigatório
        if (aluno.nome == null || aluno.nome.trim().isEmpty()) {
            System.out.println("[Erro] Nome do aluno é obrigatório!");
            throw new IllegalArgumentException("O nome do aluno é obrigatório!");
        }

        // Validação 2: Matrícula obrigatória
        if (aluno.matricula == null || aluno.matricula.trim().isEmpty()) {
            System.out.println("[Erro] Matrícula é obrigatória!");
            throw new IllegalArgumentException("A matrícula é obrigatória!");
        }

        // Validação 3: Idade válida
        if (aluno.idade <= 0) {
            System.out.println("[Erro] Idade deve ser maior que zero!");
            throw new IllegalArgumentException("A idade deve ser um valor maior que zero!");
        }

        System.out.println("LOG: Dados validados! Enviando para o AlunoRepositorio...");
        repositorio.salvar(aluno);
    }

    public List<Aluno> listarTodos() {
        return repositorio.buscarTodos();
    }

    public void deletarAluno(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula inválida para exclusão.");
        }
        repositorio.excluir(matricula);
    }
}