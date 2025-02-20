package teste_cadastro;

public class TesteCadastro {

	public static void main(String[] args) {
		CadastroPessoas cad = new CadastroPessoas();
		
		Pessoa cliente = new Cliente("Gabriel Barboza", new Data(17, 1, 2005), 1);
		Pessoa funcionario = new Funcionario("Jessica Bueno", new Data(28, 9, 2003), 7899f);
		Pessoa gerente = new Gerente("Caroliny", new Data(11, 01, 2005), 1f, "IT");
		
		cad.cadastraPessoa(cliente);
		cad.cadastraPessoa(funcionario);
		cad.cadastraPessoa(gerente);
		
		cad.imprimeCadastro();
	}

}
