package br.edu.ifrn.sge.modelo;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    public String nome;
    public String codigo;
    // Aqui nasce o vínculo: a lista que guarda os alunos desta turma!
    public List<Aluno> alunos;

    // Construtor padrão para inicializar a lista vazia e evitar erros
    public Turma() {
        this.alunos = new ArrayList<>();
    }

    public Turma(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.alunos = new ArrayList<>();
    }
}