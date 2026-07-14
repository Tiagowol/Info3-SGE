📋 Relatório Consolidado de Transição: Info3-SGE (Fase 1 ➔ Fase 2)

Este documento registra de maneira exata toda a infraestrutura instalada, os comandos de terminal executados, os erros superados e o planejamento da Fase 2 do nosso Sistema de Gestão Escolar (SGE).

---

## 💾 1. Infraestrutura e Banco de Dados (MySQL)
* [cite_start]**SGBD:** MySQL Server (local) [cite: 673]
* [cite_start]**Porta:** 3306 [cite: 673]
* [cite_start]**Banco de Dados Utilizado:** `sge_db` [cite: 673]
* [cite_start]**Usuário:** `root` [cite: 673]
* [cite_start]**Senha Homologada:** `admin` [cite: 673]

### 🗄️ Tabelas Criadas no Banco:
```sql
-- Criar a base de dados
CREATE DATABASE sge_db;

-- Usar a base
USE sge_db;

-- 1. Tabela Aluno (Fase 1 Concluída)
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    idade INT NOT NULL
);

-- 2. Tabela Turma (Fase 2 Pronta no Banco)
CREATE TABLE turma (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(50) NOT NULL UNIQUE
);
🌳 2. Estrutura Real de Diretórios do Projeto (find)
Plaintext
src/main/java
└── br
    └── edu
        └── ifrn
            └── sge
                ├── Main.java
                ├── modelo
                │   ├── Aluno.java
                │   ├── Atividade.java
                │   ├── Nota.java
                │   └── Turma.java
                ├── repositorio
                │   ├── AlunoRepositorio.java
                │   └── ConexaoBD.java
                └── servico
                    └── AlunoServico.java
🛠️ 3. Diário de Comandos Utilizados no Terminal
📂 Estruturação das Pastas (Git Bash):
Bash
# Criar as pastas de arquitetura de forma segura:
mkdir -p src/main/java/br/edu/ifrn/sge/repositorio
mkdir -p src/main/java/br/edu/ifrn/sge/servico
🧹 Limpeza e Faxina de Pastas Antigas (Maven/Lixo):
Bash
# Remover a pasta gerada automaticamente pelo Maven (br/com):
rm -rf src/main/java/br/com
☕ Compilação, Testes e Execução:
Bash
# Limpar as compilações antigas e compilar do zero:
mvn clean compile

# Rodar o projeto Java direto pelo Maven no terminal:
mvn clean compile exec:java -Dexec.mainClass="br.edu.ifrn.sge.Main"
🧠 4. Caderno de Erros Superados (Para não repetir!)
cannot find symbol (Falta de imports ou nomes errados):

O que era: O Java não achava a classe de Conexão ou os métodos do Aluno.

Como evitar: Sempre verifique se o import correto está no topo e acesse os atributos diretamente (ex: aluno.nome) caso a sua classe não utilize métodos get/set clássicos.

The declared package does not match the expected package:

O que era: Arquivos criados na pasta errada ou movidos sem atualizar a primeira linha de código.

Como evitar: A primeira linha do arquivo Java (package ...;) tem que ser exatamente o caminho físico da pasta onde ele está guardado.

Access denied for user 'root'@'localhost':

O que era: O código Java tentou acessar o MySQL usando a senha antiga "root" em vez da senha "admin" configurada no seu computador.

Como evitar: Mantenha o arquivo ConexaoBD.java atualizado com a sua senha real local.

Unknown database 'info3_sge':

O que era: O Java tentava se conectar a um banco que não existia ou com o nome incorreto.

Como evitar: Certifique-se de que a URL de conexão use o nome exato da base criada no MySQL Workbench (sge_db).

Aba "Problems" com erros fantasmas no VS Code:

O que era: O cache da extensão Java ficou travado no passado mesmo com o Maven compilando com sucesso.

Como evitar: Aperte F1 no teclado, selecione Java: Clean Java Language Server Workspace e clique em Restart.

🎯 5. Próximos Passos (Fase 2)
Para expandir o projeto aplicando a Separação de Responsabilidades (SoC) nas outras entidades, criaremos as seguintes camadas:

Turma: TurmaRepositorio.java e TurmaServico.java

Nota: NotaRepositorio.java e NotaServico.java

Atividade: AtividadeRepositorio.java and AtividadeServico.java