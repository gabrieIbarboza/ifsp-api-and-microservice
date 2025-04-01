package br.uff.ic.agenda.view;

import br.uff.ic.agenda.controller.ControleAdicionar;
import br.uff.ic.agenda.controller.ControlePersistencia;
import br.uff.ic.agenda.controller.ControlePesquisar;
import br.uff.ic.agenda.controller.ControleCarregar;
import br.uff.ic.agenda.controller.ControleOrdenar;
import br.uff.ic.agenda.controller.ControleRemover;
import br.uff.ic.agenda.controller.ControleSalvar;
import br.uff.ic.agenda.model.Contato;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

public class Agenda extends JFrame {
    
    private static final String ICONE_ADICIONA = "/toolbarButtonGraphics/general/Add16.gif";
    private static final String ICONE_REMOVE = "/toolbarButtonGraphics/general/Delete16.gif";
    
    private final DefaultListModel<Contato> contatos = new DefaultListModel<>();
    private final JList<Contato> listaContatos = new JList<>(contatos);
    private final JTextField campoNome = new JTextField();
    private final JTextField campoTelefone = new JTextField();
    private final JTextArea campoDetalhes = new JTextArea();
    private JTextField textFieldPesquisarNome;
    private JTextField campoCelular;
    private JTextField campoEndResidencial;
    private JTextField campoEndComercial;
    
    public Agenda() {
        super("Agenda");        
        montaJanela();
        new ControleOrdenar(contatos);
    }
    
    private void montaJanela() {        
        // Criando um painel com a lista de contatos
        JPanel painelLista = new JPanel(new BorderLayout());
        painelLista.setBorder(BorderFactory.createTitledBorder("Contatos"));
        listaContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaContatos);
        painelLista.add(scrollPane, BorderLayout.CENTER);
        
        // Criando um painel com os botões sob a lista
        JButton botaoAdicionar = new JButton(new ImageIcon(getClass().getResource(ICONE_ADICIONA)));
        JButton botaoRemover = new JButton(new ImageIcon(getClass().getResource(ICONE_REMOVE)));
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2));
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelLista.add(painelBotoes, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                ControleOrdenar ordenar = new ControleOrdenar(contatos);
                ordenar.ordenarLista();
        	}
        });
        btnNewButton.setIcon(new ImageIcon(Agenda.class.getResource("/toolbarButtonGraphics/general/Undo16.gif")));
        painelBotoes.add(btnNewButton);
                        
        // Criando um painel com o nome
        JPanel painelNome = new JPanel(new BorderLayout());
        painelNome.add(new JLabel("Nome:"), BorderLayout.WEST); 
        campoNome.setEnabled(false);
        painelNome.add(campoNome, BorderLayout.CENTER);
        
        // Criando um painel com o telefone
        JPanel painelTelefone = new JPanel(new BorderLayout());
        painelTelefone.add(new JLabel("Telefone:"), BorderLayout.WEST);
        campoTelefone.setEnabled(false);
        painelTelefone.add(campoTelefone, BorderLayout.CENTER);
        
        // Criando um painel que contem tanto o nome quanto o telefone
        JPanel painelCampos = new JPanel(new GridLayout(5, 1));
        painelCampos.add(painelNome);
        painelCampos.add(painelTelefone);
        
        // Criando um painel com os detalhes
        JPanel painelDetalhes = new JPanel(new BorderLayout());
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        campoDetalhes.setEnabled(false);
        painelDetalhes.add(new JScrollPane(campoDetalhes), BorderLayout.CENTER);

        // Criando um painel central que combina os campos de texto, a área de texto e os botões
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelCampos, BorderLayout.NORTH);
        painelCentral.add(painelDetalhes, BorderLayout.CENTER);
        
        // Criando um painel do tipo split, que combina a lista com os demais componentes
        JSplitPane painelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, painelLista, painelCentral);
        painelPrincipal.setDividerLocation(200);
        this.setContentPane(painelPrincipal);

        JPanel panelMostraQtd = new JPanel();
        scrollPane.setColumnHeaderView(panelMostraQtd);
        
        JLabel lblQtd = new JLabel("Quantidade total: ");
        panelMostraQtd.add(lblQtd);
        
        JPanel painelCelular = new JPanel((LayoutManager) null);
        painelCampos.add(painelCelular);
        painelCelular.setLayout(new BorderLayout());
        
        campoCelular = new JTextField();
        campoCelular.setEnabled(false);
        painelCelular.add(campoCelular, BorderLayout.CENTER);
        campoCelular.setColumns(10);
        
        JLabel lblCelular = new JLabel("Celular:");
        painelCelular.add(lblCelular, BorderLayout.WEST);
        
        JPanel painelEnderecoComercial = new JPanel((LayoutManager) null);
        painelCampos.add(painelEnderecoComercial);
        painelEnderecoComercial.setLayout(new BorderLayout());
        
        JLabel lblComercial = new JLabel("Endereco Comercial:");
        painelEnderecoComercial.add(lblComercial, BorderLayout.WEST);
        
        campoEndComercial = new JTextField();
        campoEndComercial.setEnabled(false);
        painelEnderecoComercial.add(campoEndComercial, BorderLayout.CENTER);
        campoEndComercial.setColumns(10);
        
        JPanel painelEnderecoResidencial = new JPanel((LayoutManager) null);
        painelCampos.add(painelEnderecoResidencial);
        painelEnderecoResidencial.setLayout(new BorderLayout());
        
        JLabel lblNewLabel = new JLabel("Endereco Residencial:");
        painelEnderecoResidencial.add(lblNewLabel, BorderLayout.WEST);
        
        campoEndResidencial = new JTextField();
        campoEndResidencial.setEnabled(false);
        painelEnderecoResidencial.add(campoEndResidencial, BorderLayout.CENTER);
        campoEndResidencial.setColumns(10);
        
        // Configurando os listeners
        listaContatos.addListSelectionListener(new ControleCarregar(listaContatos, campoNome, campoTelefone, campoDetalhes, lblQtd, campoCelular, campoEndComercial, campoEndResidencial));
        
        //Cria um contato
        botaoAdicionar.addActionListener(new ControleAdicionar(contatos, lblQtd)); 
        
        botaoRemover.addActionListener(new ControleRemover(listaContatos, contatos,lblQtd));
        ControleSalvar salvar = new ControleSalvar(listaContatos, campoNome, campoTelefone, campoDetalhes, lblQtd, campoCelular, campoEndComercial, campoEndResidencial);
        
        campoNome.addKeyListener(salvar);
        campoTelefone.addKeyListener(salvar);
        campoDetalhes.addKeyListener(salvar);
        this.addWindowListener(new ControlePersistencia(contatos, lblQtd));
        campoCelular.addKeyListener(salvar);
        campoEndComercial.addKeyListener(salvar);
        campoEndResidencial.addKeyListener(salvar);
        
        JPanel panelPesquisar = new JPanel();
        painelLista.add(panelPesquisar, BorderLayout.NORTH);
        
        JLabel lblNomePesquisar = new JLabel("Nome: ");
        panelPesquisar.add(lblNomePesquisar);
        
        textFieldPesquisarNome = new JTextField();
        panelPesquisar.add(textFieldPesquisarNome);
        textFieldPesquisarNome.setColumns(10);
        
        JButton btnPesquisar = new JButton("");
        btnPesquisar.setIcon(new ImageIcon(Agenda.class.getResource("/toolbarButtonGraphics/general/Zoom16.gif")));
        panelPesquisar.add(btnPesquisar);
        //para pesquisar contato
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnPesquisar.addActionListener(new ControlePesquisar(listaContatos, textFieldPesquisarNome, campoNome, campoTelefone, campoDetalhes));        		
        	}
        });
        
        this.pack();
        this.setSize(455, 358);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
            
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
    }
}