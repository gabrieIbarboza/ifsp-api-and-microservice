package teste_cadastro;

public class Data {
	private Integer dia;
	private Integer mes;
	private Integer ano;
	
	public Integer getDia() { return dia; }
	public Integer getMes() { return mes; }
	public Integer getAno() { return ano; }
	
	public Data(Integer dia, Integer mes, Integer ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	public String formatted() {
		return String.format("%02d/%02d/04%d", dia, mes, ano);
	}
	
}
