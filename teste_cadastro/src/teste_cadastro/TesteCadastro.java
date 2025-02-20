package teste_cadastro;

public class TesteCadastro {

	public static void main(String[] args) {
		Pessoa cliente = new Cliente("Gabriel Barboza", new Data(17, 01, 2005), 1);
		Pessoa funcionario = new Funcionario("Jessica Bueno", new Data(17, 01, 2005), 7899f);
		Pessoa gerente = new Gerente("Caroliny", new Data(17, 01, 2005), 1f, "Marketing");
	}

}
