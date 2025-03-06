package lista1.Exercicio21;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Emprestimo {
	private List<Livro> livros = new ArrayList<Livro>();
	private Pessoa cliente;
	private Date emprestimoData;
	
	public Emprestimo(String cliente2, List<Livro> livrosSelecionados, Date dataEmprestimo) {
		this.cliente = cliente;
		this.livros = livrosSelecionados;
		this.emprestimoData = dataEmprestimo;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public Date getEmprestimoData() {
		return emprestimoData;
	}
	public void setEmprestimoData(Date emprestimoData) {
		this.emprestimoData = emprestimoData;
	}
	
	public void gerarRecibo() {
		System.out.println("Cliente: "+cliente);
		System.out.println("Livros: ");
		for(Livro livro : livros) {
			livro.exibirInformacoes();
		}
		System.out.println("Data de empréstimo: " + this.emprestimoData + " - devolver 20 dias depois da data de empréstimo");
	}
	
}
