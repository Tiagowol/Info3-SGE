package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Nota;
import br.edu.ifrn.sge.repositorio.NotaRepositorio;
import java.util.List;

public class NotaServico {

    private NotaRepositorio repositorio = new NotaRepositorio();

    public void cadastrarNota(Nota nota) {
        // Validação 1: Garante que a nota esteja no intervalo de 0.0 a 100.0
        if (nota.valor < 0.0 || nota.valor > 100.0) {
            System.out.println("[Erro] A nota deve estar entre 0.0 e 100.0!");
            throw new IllegalArgumentException("A nota deve estar entre 0.0 e 100.0!");
        }
        
        // Validação 2: Garante que a disciplina foi preenchida
        if (nota.disciplina == null || nota.disciplina.trim().isEmpty()) {
            System.out.println("[Erro] Disciplina é obrigatória para cadastrar uma nota!");
            throw new IllegalArgumentException("A disciplina é obrigatória para cadastrar uma nota!");
        }

        System.out.println("[LOG] Dados da nota validados! Enviando para o NotaRepositorio...");
        repositorio.salvar(nota);
    }

    public List<Nota> listarTodos() {
        return repositorio.buscarTodos();
    }

    public void deletarNota(String disciplina, double valor) {
        if (disciplina == null || disciplina.trim().isEmpty()) {
            throw new IllegalArgumentException("Disciplina inválida para exclusão.");
        }
        repositorio.excluir(disciplina, valor);
    }
}