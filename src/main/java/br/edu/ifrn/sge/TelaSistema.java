package br.edu.ifrn.sge;

import br.edu.ifrn.sge.modelo.Aluno;
import br.edu.ifrn.sge.modelo.Turma;
import br.edu.ifrn.sge.modelo.Atividade;
import br.edu.ifrn.sge.modelo.Nota;
import br.edu.ifrn.sge.servico.AlunoServico;
import br.edu.ifrn.sge.servico.TurmaServico;
import br.edu.ifrn.sge.servico.AtividadeServico;
import br.edu.ifrn.sge.servico.NotaServico;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaSistema extends JFrame {

    private AlunoServico alunoServico = new AlunoServico();
    private TurmaServico turmaServico = new TurmaServico();
    private AtividadeServico atividadeServico = new AtividadeServico();
    private NotaServico notaServico = new NotaServico();

    // Listas e Índices para navegação
    private List<Aluno> listaAlunos;
    private int indiceAluno = -1;

    private List<Turma> listaTurmas;
    private int indiceTurma = -1;

    private List<Atividade> listaAtividades;
    private int indiceAtividade = -1;

    private List<Nota> listaNotas;
    private int indiceNota = -1;

    public TelaSistema() {
        setTitle("SGE - Sistema de Gestão Escolar (Info3-SGE)");
        setSize(600, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        // Carrega dados iniciais do banco
        atualizarListas();

        abas.addTab("Aluno", criarPainelAluno());
        abas.addTab("Turma", criarPainelTurma());
        abas.addTab("Atividade", criarPainelAtividade());
        abas.addTab("Nota", criarPainelNota());

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSairSistema = new JButton("Sair do Sistema");
        btnSairSistema.setBackground(new Color(220, 53, 69));
        btnSairSistema.setForeground(Color.WHITE);
        btnSairSistema.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do SGE?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        painelRodape.add(btnSairSistema);

        setLayout(new BorderLayout());
        add(abas, BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);
    }

    private void atualizarListas() {
        listaAlunos = alunoServico.listarTodos();
        listaTurmas = turmaServico.listarTodos();
        listaAtividades = atividadeServico.listarTodos();
        listaNotas = notaServico.listarTodos();
    }

    // --- PAINEL CADASTRO DE ALUNO ---
    private JPanel criarPainelAluno() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JTextField txtNome = new JTextField();
        JTextField txtMatricula = new JTextField();
        JTextField txtIdade = new JTextField();
        JLabel lblContador = new JLabel("Nenhum registro carregado", SwingConstants.CENTER);

        painelCampos.add(new JLabel("Nome do Aluno:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Matrícula:"));
        painelCampos.add(txtMatricula);
        painelCampos.add(new JLabel("Idade:"));
        painelCampos.add(txtIdade);
        painelCampos.add(new JLabel("Registro Atual:"));
        painelCampos.add(lblContador);

        // Painel de Navegação
        JPanel painelNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAnterior = new JButton("⏪ Anterior");
        JButton btnProximo = new JButton("Próximo ⏩");
        JButton btnExcluir = new JButton("🗑️ Excluir Cadastro");
        btnExcluir.setBackground(new Color(220, 53, 69));
        btnExcluir.setForeground(Color.WHITE);

        painelNavegacao.add(btnAnterior);
        painelNavegacao.add(btnProximo);
        painelNavegacao.add(btnExcluir);

        // Painel de Botões Principais
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnNovo = new JButton("Novo");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar = new JButton("Cadastrar Aluno");
        btnSalvar.setBackground(new Color(40, 167, 69));
        btnSalvar.setForeground(Color.WHITE);

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        JPanel painelSul = new JPanel(new GridLayout(2, 1));
        painelSul.add(painelNavegacao);
        painelSul.add(painelBotoes);

        // Lógica de Atualização dos Campos de Aluno
        Runnable exibirAlunoAtual = () -> {
            if (listaAlunos.isEmpty()) {
                txtNome.setText("");
                txtMatricula.setText("");
                txtIdade.setText("");
                lblContador.setText("Sem registros");
                indiceAluno = -1;
            } else {
                Aluno a = listaAlunos.get(indiceAluno);
                txtNome.setText(a.nome);
                txtMatricula.setText(a.matricula);
                txtIdade.setText(String.valueOf(a.idade));
                lblContador.setText((indiceAluno + 1) + " de " + listaAlunos.size());
            }
        };

        btnAnterior.addActionListener(e -> {
            if (!listaAlunos.isEmpty()) {
                if (indiceAluno > 0) {
                    indiceAluno--;
                    exibirAlunoAtual.run();
                }
            }
        });

        btnProximo.addActionListener(e -> {
            if (!listaAlunos.isEmpty()) {
                if (indiceAluno < listaAlunos.size() - 1) {
                    indiceAluno++;
                    exibirAlunoAtual.run();
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            if (indiceAluno >= 0 && !listaAlunos.isEmpty()) {
                Aluno a = listaAlunos.get(indiceAluno);
                int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o aluno " + a.nome + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        alunoServico.deletarAluno(a.matricula);
                        JOptionPane.showMessageDialog(this, "Aluno removido com sucesso!");
                        atualizarListas();
                        if (indiceAluno >= listaAlunos.size()) {
                            indiceAluno = listaAlunos.size() - 1;
                        }
                        exibirAlunoAtual.run();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnNovo.addActionListener(e -> {
            txtNome.setText("");
            txtMatricula.setText("");
            txtIdade.setText("");
            lblContador.setText("Preenchendo Novo...");
            txtNome.requestFocus();
        });

        btnCancelar.addActionListener(e -> {
            atualizarListas();
            if (!listaAlunos.isEmpty()) {
                indiceAluno = 0;
            }
            exibirAlunoAtual.run();
        });

        btnSalvar.addActionListener(e -> {
            try {
                Aluno aluno = new Aluno();
                aluno.nome = txtNome.getText();
                aluno.matricula = txtMatricula.getText();
                aluno.idade = Integer.parseInt(txtIdade.getText());

                alunoServico.cadastrarAluno(aluno);
                JOptionPane.showMessageDialog(this, "[Sucesso] Aluno salvo no banco!");
                atualizarListas();
                indiceAluno = listaAlunos.size() - 1; // Foca no último adicionado
                exibirAlunoAtual.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "[Erro] Verifique os dados inseridos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (!listaAlunos.isEmpty()) {
            indiceAluno = 0;
            exibirAlunoAtual.run();
        }

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);
        return painelPrincipal;
    }

    // --- PAINEL CADASTRO DE TURMA ---
    private JPanel criarPainelTurma() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JTextField txtNomeTurma = new JTextField();
        JTextField txtCodigo = new JTextField();
        JLabel lblContador = new JLabel("Nenhum registro carregado", SwingConstants.CENTER);

        painelCampos.add(new JLabel("Nome da Turma:"));
        painelCampos.add(txtNomeTurma);
        painelCampos.add(new JLabel("Código da Turma:"));
        painelCampos.add(txtCodigo);
        painelCampos.add(new JLabel("Registro Atual:"));
        painelCampos.add(lblContador);

        JPanel painelNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAnterior = new JButton("⏪ Anterior");
        JButton btnProximo = new JButton("Próximo ⏩");
        JButton btnExcluir = new JButton("🗑️ Excluir Cadastro");
        btnExcluir.setBackground(new Color(220, 53, 69));
        btnExcluir.setForeground(Color.WHITE);

        painelNavegacao.add(btnAnterior);
        painelNavegacao.add(btnProximo);
        painelNavegacao.add(btnExcluir);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnNovo = new JButton("Novo");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar = new JButton("Cadastrar Turma");
        btnSalvar.setBackground(new Color(40, 167, 69));
        btnSalvar.setForeground(Color.WHITE);

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        JPanel painelSul = new JPanel(new GridLayout(2, 1));
        painelSul.add(painelNavegacao);
        painelSul.add(painelBotoes);

        Runnable exibirTurmaAtual = () -> {
            if (listaTurmas.isEmpty()) {
                txtNomeTurma.setText("");
                txtCodigo.setText("");
                lblContador.setText("Sem registros");
                indiceTurma = -1;
            } else {
                Turma t = listaTurmas.get(indiceTurma);
                txtNomeTurma.setText(t.nome);
                txtCodigo.setText(t.codigo);
                lblContador.setText((indiceTurma + 1) + " de " + listaTurmas.size());
            }
        };

        btnAnterior.addActionListener(e -> {
            if (!listaTurmas.isEmpty() && indiceTurma > 0) {
                indiceTurma--;
                exibirTurmaAtual.run();
            }
        });

        btnProximo.addActionListener(e -> {
            if (!listaTurmas.isEmpty() && indiceTurma < listaTurmas.size() - 1) {
                indiceTurma++;
                exibirTurmaAtual.run();
            }
        });

        btnExcluir.addActionListener(e -> {
            if (indiceTurma >= 0 && !listaTurmas.isEmpty()) {
                Turma t = listaTurmas.get(indiceTurma);
                int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir a turma " + t.nome + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        turmaServico.deletarTurma(t.codigo);
                        JOptionPane.showMessageDialog(this, "Turma removida!");
                        atualizarListas();
                        if (indiceTurma >= listaTurmas.size()) {
                            indiceTurma = listaTurmas.size() - 1;
                        }
                        exibirTurmaAtual.run();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnNovo.addActionListener(e -> {
            txtNomeTurma.setText("");
            txtCodigo.setText("");
            lblContador.setText("Preenchendo Novo...");
            txtNomeTurma.requestFocus();
        });

        btnCancelar.addActionListener(e -> {
            atualizarListas();
            if (!listaTurmas.isEmpty()) {
                indiceTurma = 0;
            }
            exibirTurmaAtual.run();
        });

        btnSalvar.addActionListener(e -> {
            try {
                Turma turma = new Turma();
                turma.nome = txtNomeTurma.getText();
                turma.codigo = txtCodigo.getText();

                turmaServico.cadastrarTurma(turma);
                JOptionPane.showMessageDialog(this, "[Sucesso] Turma salva!");
                atualizarListas();
                indiceTurma = listaTurmas.size() - 1;
                exibirTurmaAtual.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (!listaTurmas.isEmpty()) {
            indiceTurma = 0;
            exibirTurmaAtual.run();
        }

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);
        return painelPrincipal;
    }

    // --- PAINEL CADASTRO DE ATIVIDADE ---
    private JPanel criarPainelAtividade() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JTextField txtNomeAtiv = new JTextField();
        JTextField txtTipo = new JTextField();
        JLabel lblContador = new JLabel("Nenhum registro carregado", SwingConstants.CENTER);

        painelCampos.add(new JLabel("Nome da Atividade:"));
        painelCampos.add(txtNomeAtiv);
        painelCampos.add(new JLabel("Tipo (Prática/Teórica):"));
        painelCampos.add(txtTipo);
        painelCampos.add(new JLabel("Registro Atual:"));
        painelCampos.add(lblContador);

        JPanel painelNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAnterior = new JButton("⏪ Anterior");
        JButton btnProximo = new JButton("Próximo ⏩");
        JButton btnExcluir = new JButton("🗑️ Excluir Cadastro");
        btnExcluir.setBackground(new Color(220, 53, 69));
        btnExcluir.setForeground(Color.WHITE);

        painelNavegacao.add(btnAnterior);
        painelNavegacao.add(btnProximo);
        painelNavegacao.add(btnExcluir);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnNovo = new JButton("Novo");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar = new JButton("Cadastrar Atividade");
        btnSalvar.setBackground(new Color(40, 167, 69));
        btnSalvar.setForeground(Color.WHITE);

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        JPanel painelSul = new JPanel(new GridLayout(2, 1));
        painelSul.add(painelNavegacao);
        painelSul.add(painelBotoes);

        Runnable exibirAtividadeAtual = () -> {
            if (listaAtividades.isEmpty()) {
                txtNomeAtiv.setText("");
                txtTipo.setText("");
                lblContador.setText("Sem registros");
                indiceAtividade = -1;
            } else {
                Atividade at = listaAtividades.get(indiceAtividade);
                txtNomeAtiv.setText(at.nome);
                txtTipo.setText(at.tipo);
                lblContador.setText((indiceAtividade + 1) + " de " + listaAtividades.size());
            }
        };

        btnAnterior.addActionListener(e -> {
            if (!listaAtividades.isEmpty() && indiceAtividade > 0) {
                indiceAtividade--;
                exibirAtividadeAtual.run();
            }
        });

        btnProximo.addActionListener(e -> {
            if (!listaAtividades.isEmpty() && indiceAtividade < listaAtividades.size() - 1) {
                indiceAtividade++;
                exibirAtividadeAtual.run();
            }
        });

        btnExcluir.addActionListener(e -> {
            if (indiceAtividade >= 0 && !listaAtividades.isEmpty()) {
                Atividade at = listaAtividades.get(indiceAtividade);
                int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir a atividade " + at.nome + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        atividadeServico.deletarAtividade(at.nome);
                        JOptionPane.showMessageDialog(this, "Atividade removida!");
                        atualizarListas();
                        if (indiceAtividade >= listaAtividades.size()) {
                            indiceAtividade = listaAtividades.size() - 1;
                        }
                        exibirAtividadeAtual.run();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnNovo.addActionListener(e -> {
            txtNomeAtiv.setText("");
            txtTipo.setText("");
            lblContador.setText("Preenchendo Novo...");
            txtNomeAtiv.requestFocus();
        });

        btnCancelar.addActionListener(e -> {
            atualizarListas();
            if (!listaAtividades.isEmpty()) {
                indiceAtividade = 0;
            }
            exibirAtividadeAtual.run();
        });

        btnSalvar.addActionListener(e -> {
            try {
                Atividade atividade = new Atividade();
                atividade.nome = txtNomeAtiv.getText();
                atividade.tipo = txtTipo.getText();

                atividadeServico.cadastrarAtividade(atividade);
                JOptionPane.showMessageDialog(this, "[Sucesso] Atividade salva!");
                atualizarListas();
                indiceAtividade = listaAtividades.size() - 1;
                exibirAtividadeAtual.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (!listaAtividades.isEmpty()) {
            indiceAtividade = 0;
            exibirAtividadeAtual.run();
        }

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);
        return painelPrincipal;
    }

    // --- PAINEL CADASTRO DE NOTA ---
    private JPanel criarPainelNota() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JTextField txtValor = new JTextField();
        JTextField txtDisciplina = new JTextField();
        JLabel lblContador = new JLabel("Nenhum registro carregado", SwingConstants.CENTER);

        painelCampos.add(new JLabel("Valor da Nota (0.0 - 100.0):"));
        painelCampos.add(txtValor);
        painelCampos.add(new JLabel("Disciplina:"));
        painelCampos.add(txtDisciplina);
        painelCampos.add(new JLabel("Registro Atual:"));
        painelCampos.add(lblContador);

        JPanel painelNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAnterior = new JButton("⏪ Anterior");
        JButton btnProximo = new JButton("Próximo ⏩");
        JButton btnExcluir = new JButton("🗑️ Excluir Cadastro");
        btnExcluir.setBackground(new Color(220, 53, 69));
        btnExcluir.setForeground(Color.WHITE);

        painelNavegacao.add(btnAnterior);
        painelNavegacao.add(btnProximo);
        painelNavegacao.add(btnExcluir);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnNovo = new JButton("Novo");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar = new JButton("Cadastrar Nota");
        btnSalvar.setBackground(new Color(40, 167, 69));
        btnSalvar.setForeground(Color.WHITE);

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        JPanel painelSul = new JPanel(new GridLayout(2, 1));
        painelSul.add(painelNavegacao);
        painelSul.add(painelBotoes);

        Runnable exibirNotaAtual = () -> {
            if (listaNotas.isEmpty()) {
                txtValor.setText("");
                txtDisciplina.setText("");
                lblContador.setText("Sem registros");
                indiceNota = -1;
            } else {
                Nota n = listaNotas.get(indiceNota);
                txtValor.setText(String.valueOf(n.valor));
                txtDisciplina.setText(n.disciplina);
                lblContador.setText((indiceNota + 1) + " de " + listaNotas.size());
            }
        };

        btnAnterior.addActionListener(e -> {
            if (!listaNotas.isEmpty() && indiceNota > 0) {
                indiceNota--;
                exibirNotaAtual.run();
            }
        });

        btnProximo.addActionListener(e -> {
            if (!listaNotas.isEmpty() && indiceNota < listaNotas.size() - 1) {
                indiceNota++;
                exibirNotaAtual.run();
            }
        });

        btnExcluir.addActionListener(e -> {
            if (indiceNota >= 0 && !listaNotas.isEmpty()) {
                Nota n = listaNotas.get(indiceNota);
                int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir a nota de " + n.valor + " em " + n.disciplina + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        notaServico.deletarNota(n.disciplina, n.valor);
                        JOptionPane.showMessageDialog(this, "Nota removida!");
                        atualizarListas();
                        if (indiceNota >= listaNotas.size()) {
                            indiceNota = listaNotas.size() - 1;
                        }
                        exibirNotaAtual.run();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnNovo.addActionListener(e -> {
            txtValor.setText("");
            txtDisciplina.setText("");
            lblContador.setText("Preenchendo Novo...");
            txtValor.requestFocus();
        });

        btnCancelar.addActionListener(e -> {
            atualizarListas();
            if (!listaNotas.isEmpty()) {
                indiceNota = 0;
            }
            exibirNotaAtual.run();
        });

        btnSalvar.addActionListener(e -> {
            try {
                Nota nota = new Nota();
                nota.valor = Double.parseDouble(txtValor.getText());
                nota.disciplina = txtDisciplina.getText();

                notaServico.cadastrarNota(nota);
                JOptionPane.showMessageDialog(this, "[Sucesso] Nota salva!");
                atualizarListas();
                indiceNota = listaNotas.size() - 1;
                exibirNotaAtual.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (!listaNotas.isEmpty()) {
            indiceNota = 0;
            exibirNotaAtual.run();
        }

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);
        return painelPrincipal;
    }
}