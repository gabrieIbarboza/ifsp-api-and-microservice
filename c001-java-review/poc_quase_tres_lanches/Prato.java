package poc_quase_tres_lanches;

import java.time.LocalDate;

abstract class Prato {
	
	private LocalDate dataValidade;
	private Double peso;
	protected Double preco;
	
	public LocalDate getDataValidade() { return dataValidade; }
	public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
	
	public Double getPeso() { return peso; }
	public void setPeso(Double peso) { this.peso = peso; }
	
	public Double getPreco() { return preco; }
	public abstract void calcularPreco();
}
