package poc_quase_tres_lanches;

public class Lanche extends Prato {
	private enumPao pao;
	private enumRecheio recheio;
	private enumMolho molho;
	
	public enumPao getPao() { return pao; }
	public enumRecheio getRecheio() { return recheio; }
	public enumMolho getMolho() { return molho; }
	
	public Lanche(enumPao pao, enumRecheio recheio, enumMolho molho) {
		super();
		this.pao = pao;
		this.recheio = recheio;
		this.molho = molho;
	}
	
	public void calcularPreco() {
		//logic
		preco = 40.00;
	}
}
