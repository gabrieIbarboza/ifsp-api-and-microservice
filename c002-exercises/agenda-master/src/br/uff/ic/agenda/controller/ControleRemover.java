package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

public class ControleRemover implements ActionListener {

    private final JList<Contato> listaContatos;
    private final DefaultListModel<Contato> contatos;
    private final JLabel qtd;
    
    public ControleRemover (JList<Contato> listaContatos, DefaultListModel<Contato> contatos, JLabel qtd) {
        this.listaContatos = listaContatos;
        this.contatos = contatos;
        this.qtd = qtd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Contato contatoSelecionado = listaContatos.getSelectedValue();
        contatos.removeElement(contatoSelecionado);
        qtd.setText("Quantidade total: "+contatos.getSize());
        
        ControleOrdenar ordenar = new ControleOrdenar(contatos);
        ordenar.ordenarLista();
    }
}