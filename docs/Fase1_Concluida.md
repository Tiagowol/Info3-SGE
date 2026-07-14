# 📋 Relatório Consolidado de Conclusão: Info3-SGE - Fase 1

Este documento registra de maneira exata toda a infraestrutura instalada, os comandos de terminal executados, a estrutura de diretórios final e as lições aprendidas com os erros superados nesta etapa.

---

## 💾 1. Infraestrutura do Banco de Dados (SGBD)
* **SGBD Instalado:** MySQL Server (Acessado localmente via MySQL Workbench / Console)
* **Host / Endereço:** `localhost` (IP `127.0.0.1`)
* **Porta de Conexão:** `3306`
* **Banco de Dados Real Criado:** `sge_db`
* **Usuário Administrativo:** `root`
* **Senha Homologada:** `admin`

### 🗄️ Comandos Executados no MySQL:
```sql
-- Garante a existência do banco de dados real usado pelo Java
CREATE DATABASE IF NOT EXISTS sge_db;

-- Entra no contexto do banco correto
USE sge_db;

-- Criação da tabela oficial mapeada pelas variáveis da classe Aluno
CREATE TABLE IF NOT EXISTS aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(30) NOT NULL UNIQUE,
    idade INT NOT NULL
);
🌳 2. Mapa Visual do Diretório Limpo (Estrutura Real do Projeto)
Após a remoção das classes de poluição iniciais do Maven e o posicionamento correto da classe principal, esta é a árvore estrutural exata contida na pasta de trabalho C:\dev\Info3-SGE:

Plaintext
Info3-SGE/                  (Raiz do Projeto)
  ├── docs/                 (Documentação de Requisitos e Sprints)
  │     └── Fase1_Concluida.md
  ├── pom.xml               (Configurações e Dependências do Driver MySQL)
  └── src/
        └── main/
              └── java/     (Diretório de Código-Fonte Base)
                    └── br/
                          └── edu/
                                └── ifrn/
                                      └── sge/
                                            ├── Main.java (Cérebro do Sistema)
                                            ├── modelo/   (Camada de Dados Puros)
                                            │     ├── Aluno.java
                                            │     ├── Atividade.java
                                            │     ├── Nota.java
                                            │     └── Turma.java
                                            ├── repositorio/ (Camada de Infraestrutura)
                                            │     ├── AlunoRepositorio.java
                                            │     └── ConexaoBD.java
                                            └── servico/  (Camada de Regras de Negócio)
                                                  └── AlunoServico.java
🛠️ 3. Diário de Comandos Utilizados no Terminal (Git Bash)
📂 Criação de Pastas e Estruturação de Pacotes:
Bash
# Criar a pasta do pacote de infraestrutura (MySQL) caso não existisse
mkdir -p src/main/java/br/edu/ifrn/sge/repositorio

# Criar a pasta do pacote de regras de negócio caso não existisse
mkdir -p src/main/java/br/edu/ifrn/sge/servico
🧹 Faxina Estrutural e Remoção de Lixo de Escopo:
Bash
# Mover a classe Main.java solta na raiz para dentro do pacote correto do IFRN
mv src/main/java/Main.java src/main/java/br/edu/ifrn/sge/

# Excluir a pasta "br/com/escola" e a classe "App.java" redundantes geradas pelo Maven
rm -rf src/main/java/br/com
🎯 Comandos de Validação Geral e Árvore de Arquivos:
Bash
# Comando mestre para desenhar todos os arquivos e conferir a árvore na tela
find src/main/java -maxdepth 10

# Comando para limpar compilações residuais antigas e reconstruir o projeto
mvn clean compile

# Comando para rodar a Fase de Teste executando a Main e gravando no MySQL
mvn clean compile exec:java -Dexec.mainClass="br.edu.ifrn.sge.Main"
🌐 Sincronização e Rastreabilidade com o GitHub:
Bash
# Preparar arquivos e registrar alterações locais
git add .
git commit -m "Fase 1 Finalizada: Estrutura alinhada e persistencia do Aluno testada"

# Publicar na nuvem do repositório remoto
git push origin main

# Conferir se o ambiente está limpo e sincronizado
git status
🧠 4. Caderno de Erros Superados (Para não cometer novamente!)
Durante o desenvolvimento da Fase 1, enfrentamos os seguintes desafios técnicos. Veja o que causou cada um e como evitar que se repitam:

❌ Erro 1: cannot find symbol / cannot be resolved
O que aconteceu: Faltava importar a classe ConexaoBD ou os nomes das variáveis acessadas não batiam com o que estava escrito na classe de destino.

Como evitar: Lembre-se sempre de conferir as linhas de import no topo do arquivo. Se a classe destino (ex: Aluno.java) tem a variável como public String nome;, acesse diretamente com aluno.nome em vez de usar aluno.getNome().

❌ Erro 2: The declared package does not match the expected package
O que aconteceu: O arquivo foi movido de pasta ou criado em um diretório físico que não batia com a primeira linha de código (package br.edu.ifrn.sge.servico;).

Como evitar: No Java, a primeira linha de código exige o endereço exato das pastas onde o arquivo mora. Se mover o arquivo usando o terminal, certifique-se de atualizar a linha package do arquivo.

❌ Erro 3: Erro Fantasma (Conflito de Cache do VS Code)
O que aconteceu: Mesmo com o código corrigido e o terminal dando BUILD SUCCESS, a aba "Problems" continuava exibindo linhas vermelhas antigas.

Como evitar: A extensão de Java do VS Code às vezes trava na memória. Para limpar, aperte F1, digite Java: Clean Java Language Server Workspace, selecione a opção e clique em Restart.

❌ Erro 4: java.sql.SQLException: Access denied for user 'root'
O que aconteceu: O código tentou entrar no MySQL usando a senha padrão "root", mas a senha real da instalação local da máquina era "admin".

Como evitar: Guarde em um local seguro os dados de instalação do seu computador. Sempre verifique as variáveis em ConexaoBD.java.

❌ Erro 5: Unknown database 'info3_sge'
O que aconteceu: O Java tentou procurar um banco que não existia ou o nome da gaveta configurada estava diferente do nome que foi criado no MySQL Workbench (sge_db).

Como evitar: Certifique-se de que a String de URL em ConexaoBD.java termine com o nome idêntico do banco: "jdbc:mysql://localhost:3306/sge_db".

Encerramento da Etapa: Arquitetura limpa, padronizada e persistência base validada com BUILD SUCCESS. Pronto para os próximos passos da Fase 2.


* **Passo 3:** Salve o arquivo apertando **Ctrl + S**.
* **Passo 4:** Abra o terminal integrado e envie esse documento definitivo para o seu GitHub executando exatamente:
```bash
🌳 2. Mapa Visual do Diretório Limpo (Estrutura Real do Projeto)
Após a remoção das classes de poluição iniciais do Maven e o posicionamento correto da classe principal, esta é a árvore estrutural exata contida na pasta de trabalho C:\dev\Info3-SGE:

Plaintext
Info3-SGE/                  (Raiz do Projeto)
  ├── docs/                 (Documentação de Requisitos e Sprints)
  │     └── Fase1_Concluida.md
  ├── pom.xml               (Configurações e Dependências do Driver MySQL)
  └── src/
        └── main/
              └── java/     (Diretório de Código-Fonte Base)
                    └── br/
                          └── edu/
                                └── ifrn/
                                      └── sge/
                                            ├── Main.java (Cérebro do Sistema)
                                            ├── modelo/   (Camada de Dados Puros)
                                            │     ├── Aluno.java
                                            │     ├── Atividade.java
                                            │     ├── Nota.java
                                            │     └── Turma.java
                                            ├── repositorio/ (Camada de Infraestrutura)
                                            │     ├── AlunoRepositorio.java
                                            │     └── ConexaoBD.java
                                            └── servico/  (Camada de Regras de Negócio)
                                                  └── AlunoServico.java
🛠️ 3. Diário de Comandos Utilizados no Terminal (Git Bash)
📂 Criação de Pastas e Estruturação de Pacotes:
Bash
# Criar a pasta do pacote de infraestrutura (MySQL) caso não existisse
mkdir -p src/main/java/br/edu/ifrn/sge/repositorio

# Criar a pasta do pacote de regras de negócio caso não existisse
mkdir -p src/main/java/br/edu/ifrn/sge/servico
🧹 Faxina Estrutural e Remoção de Lixo de Escopo:
Bash
# Mover a classe Main.java solta na raiz para dentro do pacote correto do IFRN
mv src/main/java/Main.java src/main/java/br/edu/ifrn/sge/

# Excluir a pasta "br/com/escola" e a classe "App.java" redundantes geradas pelo Maven
rm -rf src/main/java/br/com
🎯 Comandos de Validação Geral e Árvore de Arquivos:
Bash
# Comando mestre para desenhar todos os arquivos e conferir a árvore na tela
find src/main/java -maxdepth 10

# Comando para limpar compilações residuais antigas e reconstruir o projeto
mvn clean compile

# Comando para rodar a Fase de Teste executando a Main e gravando no MySQL
mvn clean compile exec:java -Dexec.mainClass="br.edu.ifrn.sge.Main"
🌐 Sincronização e Rastreabilidade com o GitHub:
Bash
# Preparar arquivos e registrar alterações locais
git add .
git commit -m "Fase 1 Finalizada: Estrutura alinhada e persistencia do Aluno testada"

# Publicar na nuvem do repositório remoto
git push origin main

# Conferir se o ambiente está limpo e sincronizado
git status
🧠 4. Caderno de Erros Superados (Para não cometer novamente!)
Durante o desenvolvimento da Fase 1, enfrentamos os seguintes desafios técnicos. Veja o que causou cada um e como evitar que se repitam:

❌ Erro 1: cannot find symbol / cannot be resolved
O que aconteceu: Faltava importar a classe ConexaoBD ou os nomes das variáveis acessadas não batiam com o que estava escrito na classe de destino.

Como evitar: Lembre-se sempre de conferir as linhas de import no topo do arquivo. Se a classe destino (ex: Aluno.java) tem a variável como public String nome;, acesse diretamente com aluno.nome em vez de usar aluno.getNome().

❌ Erro 2: The declared package does not match the expected package
O que aconteceu: O arquivo foi movido de pasta ou criado em um diretório físico que não batia com a primeira linha de código (package br.edu.ifrn.sge.servico;).

Como evitar: No Java, a primeira linha de código exige o endereço exato das pastas onde o arquivo mora. Se mover o arquivo usando o terminal, certifique-se de atualizar a linha package do arquivo.

❌ Erro 3: Erro Fantasma (Conflito de Cache do VS Code)
O que aconteceu: Mesmo com o código corrigido e o terminal dando BUILD SUCCESS, a aba "Problems" continuava exibindo linhas vermelhas antigas.

Como evitar: A extensão de Java do VS Code às vezes trava na memória. Para limpar, aperte F1, digite Java: Clean Java Language Server Workspace, selecione a opção e clique em Restart.

❌ Erro 4: java.sql.SQLException: Access denied for user 'root'
O que aconteceu: O código tentou entrar no MySQL usando a senha padrão "root", mas a senha real da instalação local da máquina era "admin".

Como evitar: Guarde em um local seguro os dados de instalação do seu computador. Sempre verifique as variáveis em ConexaoBD.java.

❌ Erro 5: Unknown database 'info3_sge'
O que aconteceu: O Java tentou procurar um banco que não existia ou o nome da gaveta configurada estava diferente do nome que foi criado no MySQL Workbench (sge_db).

Como evitar: Certifique-se de que a String de URL em ConexaoBD.java termine com o nome idêntico do banco: "jdbc:mysql://localhost:3306/sge_db".

Encerramento da Etapa: Arquitetura limpa, padronizada e persistência base validada com BUILD SUCCESS. Pronto para os próximos passos da Fase 2.


* **Passo 3:** Salve o arquivo apertando **Ctrl + S**.
* **Passo 4:** Abra o terminal integrado e envie esse documento definitivo para o seu GitHub executando exatamente:
```bash
git add . && git commit -m "Docs: Adicionado relatorio consolidado de encerramento da Fase 1" && git push origin main