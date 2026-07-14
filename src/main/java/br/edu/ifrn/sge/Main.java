package br.edu.ifrn.sge;

import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.modelo.Turma;
import br.edu.ifrn.sge.modelo.Atividade;
import br.edu.ifrn.sge.modelo.Nota;

import br.edu.ifrn.sge.servico.AlunoServico;
import br.edu.ifrn.sge.servico.TurmaServico;
import br.edu.ifrn.sge.servico.AtividadeServico;
import br.edu.ifrn.sge.servico.NotaServico;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n--- [INICIANDO SISTEMA ESCOLAR Info3-SGE - FASE 2 COMPLETA] ---");

        // 1. Instanciando as camadas de serviço
        AlunoServico alunoServico = new AlunoServico();
        TurmaServico turmaServico = new TurmaServico();
        AtividadeServico atividadeServico = new AtividadeServico();
        NotaServico notaServico = new NotaServico();

        // 2. Fluxo: Aluno
        System.out.println("\n[Fluxo 1] Cadastrando Aluno...");
        Aluno aluno = new Aluno();
        aluno.nome = "Tiago Wagner";
        aluno.matricula = "202610123"; // Mude o número se der erro de UNIQUE/duplicado no teste
        aluno.idade = 17;
        alunoServico.cadastrarAluno(aluno);

        // 3. Fluxo: Turma
        System.out.println("\n[Fluxo 2] Cadastrando Turma...");
        Turma turma = new Turma();
        turma.nome = "Informatica para Internet - Info3v";
        turma.codigo = "INFO3V-2026";
        turmaServico.cadastrarTurma(turma);

        // 4. Fluxo: Atividade
        System.out.println("\n[Fluxo 3] Cadastrando Atividade...");
        Atividade atividade = new Atividade();
        atividade.nome = "Projeto Tiago JAVA MAVEN";
        atividade.tipo = "Pratica";
        atividadeServico.cadastrarAtividade(atividade);

        // 5. Fluxo: Nota
        System.out.println("\n[Fluxo 4] Cadastrando Nota...");
        Nota nota = new Nota();
        nota.valor = 10.0;
        nota.disciplina = "Desenvolvimento de Sistemas";
        notaServico.cadastrarNota(nota);

        System.out.println("\n--- [TODOS OS TESTES DE PERSISTENCIA CONCLUIDOS COM SUCESSO] ---");
    }
}