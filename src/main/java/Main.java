import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.modelo.Turma;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO SISTEMA ESCOLAR IFRN ---");

        // 1. Criando um Aluno
        Aluno aluno1 = new Aluno();
        aluno1.nome = "Tiago Wagner";
        aluno1.matricula = "202610123";
        aluno1.idade = 12;

        // 2. Criando uma Turma
        Turma turma3v = new Turma();
        turma3v.nome = "Informatica 3V";
        turma3v.codigo = "INF3V-2026";

        // 3. Vinculando o Aluno na Turma!
        turma3v.listaDeAlunos.add(aluno1);

        // 4. Mostrando o resultado na tela
        System.out.println("Turma criada: " + turma3v.nome);
        System.out.println("Aluno matriculado com sucesso: " + turma3v.listaDeAlunos.get(0).nome);
        System.out.println("---------------------------------------");
    }
}