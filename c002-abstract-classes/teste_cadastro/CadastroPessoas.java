package teste_cadastro;

import java.util.ArrayList;
import java.util.Iterator;

public class CadastroPessoas {
	private Integer qtdAtual;
	private ArrayList<Pessoa> pessoas;
	
	public CadastroPessoas() {
		qtdAtual = 0;
		pessoas = new ArrayList<Pessoa>();
	}
	
	public void cadastraPessoa(Pessoa pess) {
		pessoas.add(pess);
		qtdAtual++;
	}
	
	public void imprimeCadastro() {
		for (Iterator<Pessoa> iterator = pessoas.iterator(); iterator.hasNext();) {
			Pessoa pessoa = (Pessoa) iterator.next();
			pessoa.imprimeDados();
		}
	}
}
