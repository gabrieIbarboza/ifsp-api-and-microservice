package lista1.Exercicio19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

public class Supermercado {
	public static void main(String[] args) {
		Produto ovo = new Produto("Ovo", 10.00, 20);
		Produto farinha = new Produto("Farinha de trigo", 8.00, 35);
		Produto leite = new Produto("Leite", 4.10, 58);
		
		Scanner scanner = new Scanner(System.in);
		List<Produto> estoque = new ArrayList<Produto>();
		estoque.add(ovo);
		estoque.add(farinha);
		estoque.add(leite);
		
		List<Produto> produtosPedidos = new ArrayList<Produto>();
		String continuar = "s";
		
		do {
			System.out.println("Escolha um item abaixo:");
			int i = 1;
			for(Produto prod : estoque) {
				System.out.println("\n"+(i++)+" - "+prod.getNome()+" - R$"+prod.getPreco()+"\nQuantidade: "+prod.getQtd());
			}
			int decisao = scanner.nextInt();
			
			switch (decisao) {
			case 1: 
				produtosPedidos.add(ovo);
				estoque.get(0).setQtd(estoque.get(0).getQtd() - 1);
				break;
			case 2: 
				produtosPedidos.add(farinha);
				estoque.get(1).setQtd(estoque.get(1).getQtd() - 1);
				break;
			case 3: 
				produtosPedidos.add(leite);
				estoque.get(2).setQtd(estoque.get(2).getQtd() - 1);
				break;	
			default:
				throw new IllegalArgumentException("Unexpected value: " + decisao);
			}

			scanner.nextLine();
			System.out.println("\nAdicionar mais itens? (s/n)\n\n");
			continuar = scanner.nextLine();
			
		} while (continuar.equalsIgnoreCase("s"));
		
		
		System.out.println("\nNome do cliente:");
		String cliente = scanner.nextLine();
		
		System.out.println("\nForma de pagamento:");
		System.out.println("\nDinheiro");
		System.out.println("\nCheque");
		System.out.println("\nCartão");
		String pagamento = scanner.nextLine();
		
		Pedido pedido = new Pedido(cliente, produtosPedidos, pagamento);
		pedido.calcularTotal();
		pedido.gerarRecibo();
		
		
	}
}
