package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControleCarregar implements ListSelectionListener {

    private final JList<Contato> listaContatos;
    private final JTextField campoNome;
    private final JTextField campoTelefone;
    private final JTextArea campoDetalhes;
    private final JLabel qtdd;
    private final JTextField campoCelular;
    private final JTextField campoEndComercial;
    private final JTextField campoEndResidencial;
    
    public ControleCarregar (JList<Contato> listaContatos, JTextField campoNome, JTextField campoTelefone, JTextArea campoDetalhes, JLabel qtdd, JTextField campoCelular, JTextField campoEndComercial, JTextField campoEndResidencial) {
        this.listaContatos = listaContatos;
        this.campoNome = campoNome;
        this.campoTelefone = campoTelefone;
        this.campoDetalhes = campoDetalhes;
        this.qtdd = qtdd;
        this.campoCelular = campoCelular;
        this.campoEndComercial = campoEndComercial;
        this.campoEndResidencial = campoEndResidencial;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Contato contatoSelecionado = listaContatos.getSelectedValue();
        
        if (contatoSelecionado != null) {
            campoNome.setText(contatoSelecionado.getNome());
            campoTelefone.setText(contatoSelecionado.getTelefone());
            campoDetalhes.setText(contatoSelecionado.getDetalhes());
            campoCelular.setText(contatoSelecionado.getCelular());
            campoEndComercial.setText(contatoSelecionado.getEndComercial());
            campoEndResidencial.setText(contatoSelecionado.getEndResidencial());
        } else {
            campoNome.setText("");
            campoTelefone.setText("");
            campoDetalhes.setText("");
            campoCelular.setText("");
            campoEndComercial.setText("");
            campoEndResidencial.setText("");
        }

        campoNome.setEnabled(contatoSelecionado != null);
        campoTelefone.setEnabled(contatoSelecionado != null);
        campoDetalhes.setEnabled(contatoSelecionado != null);
        qtdd.setText("Quantidade total: "+listaContatos.getModel().getSize());
        campoCelular.setEnabled(contatoSelecionado != null);
        campoEndComercial.setEnabled(contatoSelecionado != null);
        campoEndResidencial.setEnabled(contatoSelecionado != null);
        
        listaContatos.repaint();        
    }
}