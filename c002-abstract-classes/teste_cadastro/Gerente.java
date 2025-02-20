package teste_cadastro;

public class Gerente extends Funcionario {
	
	private String area;

	public Gerente(String nome, Data nascimento, Float salario, String area) {
		super(nome, nascimento, salario);
		this.area = area;
	}
	
	public void imprimeDados() {
		System.out.println("==============================");
        System.out.println("Gerente: " + super.getNome());
        System.out.println("  Birth: " + super.getNascimento().formatted());
        System.out.println("   Area: " + area);
        System.out.printf("Salario: $%.2f\n", super.getSalario());
        System.out.printf("Imposto: $%.2f\n", calculaImposto());
	}
	
	public Float calculaImposto() {
		return super.getSalario() * 0.05f;
	}

}
