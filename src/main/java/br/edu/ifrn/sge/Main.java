package br.edu.ifrn.sge;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicializa a interface gráfica de forma segura na Thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            TelaSistema tela = new TelaSistema();
            tela.setVisible(true);
        });
    }
}