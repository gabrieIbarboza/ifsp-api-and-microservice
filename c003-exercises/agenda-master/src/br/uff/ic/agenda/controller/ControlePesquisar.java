package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlePesquisar implements ActionListener {
    private final JList<Contato> listaContatos;
    private final JTextField textFieldPesquisarNome;
    private final JTextField campoNome;
    private final JTextField campoTelefone;
    private final JTextArea campoDetalhes;

    public ControlePesquisar(JList<Contato> listaContatos, JTextField textFieldPesquisarNome, JTextField campoNome,
			JTextField campoTelefone, JTextArea campoDetalhes) {
		this.listaContatos = listaContatos;
		this.textFieldPesquisarNome = textFieldPesquisarNome;
		this.campoNome = campoNome;
		this.campoTelefone = campoTelefone;
		this.campoDetalhes = campoDetalhes;
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        String nome = textFieldPesquisarNome.getText().trim();
        
        for (int i = 0; i < listaContatos.getModel().getSize(); i++) {
            Contato contato = listaContatos.getModel().getElementAt(i);
            if (contato.getNome().equalsIgnoreCase(nome)) {
                listaContatos.setSelectedIndex(i);
                listaContatos.ensureIndexIsVisible(i);
                campoNome.setText(contato.getNome());
                campoTelefone.setText(contato.getTelefone());
                campoDetalhes.setText(contato.getDetalhes());
                return;
            } else {
                campoNome.setText("");
                campoTelefone.setText("");
                campoDetalhes.setText("");
            }
        }
        
        JOptionPane.showMessageDialog(null, "Contato não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
