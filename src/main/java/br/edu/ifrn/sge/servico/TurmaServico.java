package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Turma;
import br.edu.ifrn.sge.repositorio.TurmaRepositorio;
import java.util.List;

public class TurmaServico {

    private TurmaRepositorio repositorio = new TurmaRepositorio();

    public void cadastrarTurma(Turma turma) {
        // Validação 1: Nome da turma obrigatório
        if (turma.nome == null || turma.nome.trim().isEmpty()) {
            System.out.println("[Erro] Nome da turma é obrigatório!");
            throw new IllegalArgumentException("O nome da turma é obrigatório!");
        }

        // Validação 2: Código da turma obrigatório
        if (turma.codigo == null || turma.codigo.trim().isEmpty()) {
            System.out.println("[Erro] Código da turma é obrigatório!");
            throw new IllegalArgumentException("O código da turma é obrigatório!");
        }

        System.out.println("[LOG] Dados da turma validados! Enviando para o TurmaRepositorio...");
        repositorio.salvar(turma);
    }

    public List<Turma> listarTodos() {
        return repositorio.buscarTodos();
    }

    public void deletarTurma(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código inválido para exclusão.");
        }
        repositorio.excluir(codigo);
    }
}