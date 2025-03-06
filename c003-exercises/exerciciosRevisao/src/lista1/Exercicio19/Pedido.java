package lista1.Exercicio19;

import java.util.Iterator;
import java.util.List;

public class Pedido {
	private String nomeCliente;
	private List<Produto> produtos;
	private Double valorTotal = 0.00;
	private String formaPagamento;
		
	public Pedido(String cliente, List<Produto> produtosPedidos, String pagamento) {
		this.nomeCliente = cliente;
		this.produtos = produtosPedidos;
		this.formaPagamento = pagamento;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void calcularTotal() {
		for(Produto produto : this.produtos) {
			this.valorTotal += produto.getPreco();
		}
	}
	
	public void gerarRecibo() {
		System.out.println("\n\n----------- RECIBO ------------");
		System.out.println("\nCLIENTE:"+nomeCliente);
		System.out.println("\nPRODUTOS:");
		for (Produto produto : this.produtos) {
			System.out.println("\n  "+produto.getNome());
			System.out.println("  "+produto.getPreco());
		}
		System.out.println("\nForma de pagamento: "+formaPagamento);
		System.out.println("\nTOTAL: R$"+valorTotal);
		System.out.println("\n\n-------------------------------");
	}
	
	
}
