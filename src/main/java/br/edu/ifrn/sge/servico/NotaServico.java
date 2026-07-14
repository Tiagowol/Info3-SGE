package br.edu.ifrn.sge.servico;

import br.edu.ifrn.sge.modelo.Nota;
import br.edu.ifrn.sge.repositorio.NotaRepositorio;

public class NotaServico {

    private NotaRepositorio repositorio = new NotaRepositorio();

    public void cadastrarNota(Nota nota) {
        // Validação 1: Garante que a nota esteja no intervalo escolar padrão de 0 a 10
        if (nota.valor < 0 || nota.valor > 10) {
            System.out.println("[Erro] A nota deve estar entre 0.0 e 10.0!");
            return;
        }
        
        // Validação 2: Garante que a disciplina foi preenchida
        if (nota.disciplina == null || nota.disciplina.isEmpty()) {
            System.out.println("[Erro] Disciplina e obrigatoria para cadastrar uma nota!");
            return;
        }

        System.out.println("[LOG] Dados da nota validados! Enviando para o NotaRepositorio...");
        repositorio.salvar(nota);
    }
}