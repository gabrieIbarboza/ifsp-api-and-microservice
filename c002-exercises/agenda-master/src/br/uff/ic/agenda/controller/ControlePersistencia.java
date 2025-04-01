package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class ControlePersistencia extends WindowAdapter {

    private static final String NOME_ARQUIVO = "agenda.dat";

    private final DefaultListModel<Contato> contatos;
    private final JLabel qtd;

    public ControlePersistencia(DefaultListModel<Contato> contatos, JLabel qtd) {
        this.contatos = contatos;
        this.qtd = qtd;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        try (FileInputStream fileIn = new FileInputStream(NOME_ARQUIVO);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {            
            for (Object objeto : (Object[])in.readObject()) {
                contatos.addElement((Contato)objeto);
                qtd.setText("Quantidade total: "+contatos.getSize());

            }
//            System.out.println(""+contatos.getElementAt(1).getNome());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ControlePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try (FileOutputStream fileOut = new FileOutputStream(NOME_ARQUIVO);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(contatos.toArray());
        } catch (IOException ex) {
            Logger.getLogger(ControlePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}