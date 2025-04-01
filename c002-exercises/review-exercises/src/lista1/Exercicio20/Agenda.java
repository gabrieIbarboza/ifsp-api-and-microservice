package lista1.Exercicio20;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	private List<Contato> listaContatos = new ArrayList<Contato>();
	private Integer qtd = 0;
	
	public List<Contato> getListaContatos() {
		return listaContatos;
	}
	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	public void addContato(Contato contato) {
		listaContatos.add(contato);
		qtd++;
	}
	
	public void visualizarLista() {
		for(Contato pessoa : listaContatos) {
			System.out.println("Nome: "+pessoa.getNome());
			System.out.println("Número: "+pessoa.getNumero());
			System.out.println("------------------\n");
		}
	}
	
}
