package poc_quase_tres_lanches;

public class Salgado extends Prato {
	private enumRecheio recheio;
	private enumMassa massa;
	private enumTipo tipo;
	
	public enumRecheio getRecheio() { return recheio; }
	public enumMassa getMassa() { return massa; }
	public enumTipo getTipo() { return tipo; }
	
	public Salgado(enumRecheio recheio, enumMassa massa, enumTipo tipo) {
		super();
		this.recheio = recheio;
		this.massa = massa;
		this.tipo = tipo;
	}
	
	public void calcularPreco() {
		//logic
		preco = 20.00;
	}
}
