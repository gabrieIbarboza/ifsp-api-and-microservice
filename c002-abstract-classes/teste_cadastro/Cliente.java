package teste_cadastro;

public class Cliente extends Pessoa {
	
	private Integer codigo;

	public Cliente(String nome, Data nascimento, Integer codigo) {
		super(nome, nascimento);
		this.codigo = codigo;
	}

	@Override
	public void imprimeDados() {
        System.out.println("==============================");
        System.out.println("Cliente: " + super.getNome());
        System.out.println("  Birth: " + super.getNascimento().formatted());
        System.out.println("    Cod: " + codigo);
	}

}
