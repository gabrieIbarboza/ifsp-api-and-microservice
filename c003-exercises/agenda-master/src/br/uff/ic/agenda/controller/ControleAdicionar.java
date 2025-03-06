package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class ControleAdicionar implements ActionListener {

    private final DefaultListModel<Contato> contatos;
    private final JLabel qtd;
    
    public ControleAdicionar (DefaultListModel<Contato> contatos, JLabel qtd) {
        this.contatos = contatos;
        this.qtd = qtd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contatos.addElement(new Contato());
        qtd.setText("Quantidade total: "+contatos.getSize());
        
        ControleOrdenar ordenar = new ControleOrdenar(contatos);
        ordenar.ordenarLista();
    }
}