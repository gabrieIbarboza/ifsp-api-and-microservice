package teste_cadastro;

public abstract class Pessoa {
	private String nome;
	private Data nascimento;
	
	protected String getNome() { return nome; }
	protected Data getNascimento() { return nascimento; }
	
	public Pessoa(String nome, Data nascimento) {
		this.nome = nome;
		this.nascimento = nascimento;
	}
	
	public abstract void imprimeDados();
}
