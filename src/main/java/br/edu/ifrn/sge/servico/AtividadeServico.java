package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Atividade;
import br.edu.ifrn.sge.repositorio.AtividadeRepositorio;
import java.util.List;

public class AtividadeServico {

    private AtividadeRepositorio repositorio = new AtividadeRepositorio();

    public void cadastrarAtividade(Atividade atividade) {
        // Validação 1: Nome da atividade obrigatório
        if (atividade.nome == null || atividade.nome.trim().isEmpty()) {
            System.out.println("[Erro] Nome da atividade é obrigatório!");
            throw new IllegalArgumentException("O nome da atividade é obrigatório!");
        }

        // Validação 2: Tipo da atividade obrigatório
        if (atividade.tipo == null || atividade.tipo.trim().isEmpty()) {
            System.out.println("[Erro] Tipo da atividade é obrigatório!");
            throw new IllegalArgumentException("O tipo da atividade é obrigatório!");
        }

        System.out.println("[LOG] Dados da atividade validados! Enviando para o AtividadeRepositorio...");
        repositorio.salvar(atividade);
    }

    public List<Atividade> listarTodos() {
        return repositorio.buscarTodos();
    }

    public void deletarAtividade(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido para exclusão.");
        }
        repositorio.excluir(nome);
    }
}