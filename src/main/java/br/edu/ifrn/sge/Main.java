package br.edu.ifrn.sge; // Linha obrigatória que resolve o erro de pacote!

import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.servico.AlunoServico;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO SISTEMA ESCOLAR Info3-SGE ---");

        // 1. Criando o objeto Aluno com seus dados reais para o teste
        Aluno aluno1 = new Aluno();
        aluno1.nome = "Tiago Wagner";
        aluno1.matricula = "202610123";
        aluno1.idade = 12;

        // 2. Instanciando a nossa camada de serviço (o cérebro de validação)
        AlunoServico servico = new AlunoServico();

        System.out.println("LOG: Solicitando o cadastro do aluno: " + aluno1.nome);
        
        // 3. Executando o fluxo completo: Validação -> Repositório -> Gravação no MySQL
        servico.cadastrarAluno(aluno1);

        System.out.println("--- PROCESSO CONCLUÍDO ---");
    }
}