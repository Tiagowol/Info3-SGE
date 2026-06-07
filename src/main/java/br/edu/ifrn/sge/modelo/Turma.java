package br.edu.ifrn.sge.modelo;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    // Atributos básicos
    public String nome;
    public String codigo;

    // O Vínculo: Uma lista para guardar os alunos desta turma!
    public List<Aluno> listaDeAlunos = new ArrayList<>();
}