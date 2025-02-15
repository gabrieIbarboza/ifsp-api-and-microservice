package poc_quase_tres_lanches;

public class Pizza extends Prato {
	private enumMolho molho;
	private enumRecheio recheio;
	private enumBorda borda;
	
	public enumMolho getMolho() { return molho; }
	public enumRecheio getRecheio() { return recheio; }
	public enumBorda getBorda() { return borda; }

	public Pizza(enumMolho molho, enumRecheio recheio, enumBorda borda) {
		super();
		this.molho = molho;
		this.recheio = recheio;
		this.borda = borda;
	}

	public void calcularPreco() {
		//logic
		preco = 80.00;
	}
}
