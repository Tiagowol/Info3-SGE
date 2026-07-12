package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.repositorio.AlunoRepositorio;

public class AlunoServico {

    // Criando uma pecinha do repositório para usar aqui dentro
    private AlunoRepositorio repositorio = new AlunoRepositorio();

    // Método que faz a validação antes de mandar salvar
    public void cadastrarAluno(Aluno aluno) {
        // Regra de validação simples: não aceita aluno sem nome ou sem matrícula
        if (aluno.nome == null || aluno.nome.isEmpty() || aluno.matricula == null || aluno.matricula.isEmpty()) {
            System.out.println("ERRO: Nome e Matrícula são obrigatórios para o cadastro!");
            return;
        }

        System.out.println("LOG: Dados validados! Enviando para o AlunoRepositorio...");
        
        // Se passar na validação, manda o repositório salvar no banco de dados MySQL
        repositorio.salvar(aluno);
    }
}