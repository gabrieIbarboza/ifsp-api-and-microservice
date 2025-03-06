package lista1.Exercicio21;

public class Livro {
	private Integer codigo;
	private String nomeLivro;
	private String autor;
	private Boolean emprestado = false;
	
	public Livro(Integer codigo, String nomeLivro, String autora) {
		this.nomeLivro = nomeLivro;
		this.codigo = codigo;
		this.autor = autora;
	}

	public Livro() {
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Boolean getEmprestado() {
		return emprestado;
	}

	public void setEmprestado(Boolean emprestado) {
		this.emprestado = emprestado;
	}
	
	public void exibirInformacoes() {
		System.out.println(codigo+" - " + nomeLivro + " (Autor: " + autor + ")");
	}
	
}
