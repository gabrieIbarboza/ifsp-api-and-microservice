package br.uff.ic.agenda.controller;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import br.uff.ic.agenda.model.Contato;

public class ControleOrdenar{
    private final DefaultListModel<Contato> contatos;

    public ControleOrdenar(DefaultListModel<Contato> contatos) {
        this.contatos = contatos;
    }

    public void ordenarLista() {
    	ArrayList<Contato> listaOrdenada = new ArrayList<>();

        for (int i = 0; i < contatos.getSize(); i++) {
            listaOrdenada.add(contatos.getElementAt(i));
        }

        listaOrdenada.sort(Comparator.comparing(Contato::getNome, String.CASE_INSENSITIVE_ORDER));

        contatos.clear();
        for (Contato contato : listaOrdenada) {
            contatos.addElement(contato);
        }
    }
}
