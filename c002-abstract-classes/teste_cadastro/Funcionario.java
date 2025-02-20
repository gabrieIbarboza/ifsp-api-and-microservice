package teste_cadastro;

public class Funcionario extends Pessoa {
	
	private Float salario;

	protected Float getSalario() { return salario; }

	public Funcionario(String nome, Data nascimento, Float salario) {
		super(nome, nascimento);
		this.salario = salario;
	}

	@Override
	public void imprimeDados() {
		System.out.println("==============================");
        System.out.println("Funcionario: " + super.getNome());
        System.out.println("      Birth: " + super.getNascimento().formatted());
        System.out.printf("    Salario: $%.2f\n", salario);
        System.out.printf("    Imposto: $%.2f\n", calculaImposto());
	}
	
	public Float calculaImposto() {
		return salario * 0.03f;
	}

}
