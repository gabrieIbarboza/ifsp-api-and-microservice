package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControleSalvar extends KeyAdapter {

    private final JList<Contato> listaContatos;
    private final JTextField campoNome;
    private final JTextField campoTelefone;
    private final JTextArea campoDetalhes;
    private final JLabel qtd;
    private final JTextField campoCelular;
    private final JTextField campoEndComercial;
    private final JTextField campoEndResidencial;
    
    public ControleSalvar (JList<Contato> listaContatos, JTextField campoNome, JTextField campoTelefone, JTextArea campoDetalhes, JLabel qtd, JTextField campoCelular, JTextField campoEndComercial, JTextField campoEndResidencial) {
        this.listaContatos = listaContatos;
        this.campoNome = campoNome;
        this.campoTelefone = campoTelefone;
        this.campoDetalhes = campoDetalhes;
        this.qtd = qtd;
        this.campoCelular = campoCelular;
        this.campoEndComercial = campoEndComercial;
        this.campoEndResidencial = campoEndResidencial;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        Contato contatoSelecionado = listaContatos.getSelectedValue();
        if (contatoSelecionado != null) {
            contatoSelecionado.setNome(campoNome.getText());
            contatoSelecionado.setTelefone(campoTelefone.getText());
            contatoSelecionado.setDetalhes(campoDetalhes.getText());
            qtd.setText("Quantidade total: "+listaContatos.getSize());
            contatoSelecionado.setCelular(campoCelular.getText());
            contatoSelecionado.setEndComercial(campoEndComercial.getText());
            contatoSelecionado.setEndResidencial(campoEndResidencial.getText());
        }
        listaContatos.repaint();    
    }    
}