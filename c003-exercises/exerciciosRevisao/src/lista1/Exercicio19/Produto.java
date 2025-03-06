package lista1.Exercicio19;

public class Produto {
	private String nome;
	private Double preco;
	private Integer qtd;

	public Produto(String nome, Double preco, Integer qtd) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.qtd = qtd;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	
}
