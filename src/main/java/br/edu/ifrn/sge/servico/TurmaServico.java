package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Turma;
import br.edu.ifrn.sge.repositorio.TurmaRepositorio;

public class TurmaServico {
    
    private TurmaRepositorio repositorio = new TurmaRepositorio();

    public void cadastrarTurma(Turma turma) {
        // Regra de validação: nome e código da turma não podem ser vazios ou nulos
        if (turma.nome == null || turma.nome.isEmpty() || turma.codigo == null || turma.codigo.isEmpty()) {
            System.out.println("[Erro] Nome e Codigo sao obrigatorios para cadastrar uma turma!");
            return;
        }

        System.out.println("[LOG] Dados da turma validados! Enviando para o TurmaRepositorio...");
        repositorio.salvar(turma);
    }
}