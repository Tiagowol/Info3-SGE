package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Atividade;
import br.edu.ifrn.sge.repositorio.AtividadeRepositorio;

public class AtividadeServico {

    private AtividadeRepositorio repositorio = new AtividadeRepositorio();

    public void cadastrarAtividade(Atividade atividade) {
        if (atividade.nome == null || atividade.nome.isEmpty() || atividade.tipo == null || atividade.tipo.isEmpty()) {
            System.out.println("[Erro] Nome e Tipo sao obrigatorios para cadastrar uma atividade!");
            return;
        }

        System.out.println("[LOG] Dados da atividade validados! Enviando para o AtividadeRepositorio...");
        repositorio.salvar(atividade);
    }
}