package poc_quase_tres_lanches;

import java.util.ArrayList;

public class Pedido {
	private String nomeCliente;
	private Double taxaServico = 0.1;
	private ArrayList<Prato> itensConsumidos = new ArrayList<Prato>();
	
	public String getNomeCliente() { return nomeCliente; }
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
	
	public Double getTaxaServico() { return taxaServico * 100; }
	public void setTaxaServico(Double taxaServico) { this.taxaServico = taxaServico / 100; }
	
	private Double calcularTotal() {
		if(!itensConsumidos.isEmpty())
		{
			Double total = (double) 0;
			for (Prato prato : itensConsumidos) {
				total += prato.getPreco();
			}
			total = total * (1 + taxaServico);
			return total;
		}
		return (double) 0;
	}
	
	public void adicionarItem(Prato item) {
		itensConsumidos.add(item);
	}
	
	public Boolean removerItem(Prato item) {
		return itensConsumidos.remove(item);
	}
	
	public void emitirNotaFiscal() {
		Double total = calcularTotal();
		System.out.println("==============================");
        System.out.println("        NOTA FISCAL");
        System.out.println("==============================");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("--------------------------------");
        
        if(!itensConsumidos.isEmpty()) {
        	for (Prato prato : itensConsumidos) {
            	String descricao = gerarDescricao(prato);
            	Double preco = prato.getPreco();
                System.out.printf("%s: R$ %.2f\n", descricao, preco);
            }
        }
        
        System.out.println("--------------------------------");
        System.out.printf("Taxa de Serviço (%.0f%%)\n", this.getTaxaServico());
        System.out.printf("Total: R$ %.2f\n", total);
        System.out.println("==============================");
	}
	
	public void calcularTroco(Double dinheiroRecebido) {
		Double total = calcularTotal();
		if (dinheiroRecebido < total) {
            System.out.println("Valor insuficiente para o pagamento!");
        } else {
            Double troco = dinheiroRecebido - total;
            System.out.printf("Troco: R$ %.2f\n", troco);
        }
	}
	
	private String gerarDescricao(Prato prato) {
		String descricao = String.format("%s - %.2fkg, %s", prato.getClass().getSimpleName(), prato.getPeso(), prato.getDataValidade());

	    if (prato instanceof Pizza pizza) {
	        descricao += String.format(" | Molho: %s, Recheio: %s, Borda: %s", pizza.getMolho(), pizza.getRecheio(), pizza.getBorda());
	    } 
	    else if (prato instanceof Salgado salgado) {
	        descricao += String.format(" | Recheio: %s, Massa: %s, Tipo: %s", salgado.getRecheio(), salgado.getMassa(), salgado.getTipo());
	    } 
	    else if (prato instanceof Lanche lanche) {
	        descricao += String.format(" | Pão: %s, Recheio: %s, Molho: %s", lanche.getPao(), lanche.getRecheio(), lanche.getMolho());
	    }
	    
	    return descricao;
	}
}
