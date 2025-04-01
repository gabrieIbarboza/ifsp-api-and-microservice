package lista1.Exercicio4;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String nomeProduto;
		Double preco;
		int quantidade;
		
		System.out.println("Digite o nome do produto: ");
		nomeProduto = scanner.nextLine();
		System.out.println("Digite preço total: ");
		preco = scanner.nextDouble();
		System.out.println("Digite o número de unidades");
		quantidade = scanner.nextInt();
		Double novoPreco = calcularDesconto(preco, quantidade);
		System.out.println("Sua compra de " + nomeProduto + "ficou no valor de " + novoPreco);
		
		scanner.close();
	}
	
	public static Double calcularDesconto(Double preco, int quantidade) {
		if(quantidade >= 10 && quantidade <= 20) {
			System.out.println("Você recebeu 10% de desconto!");
			return preco - (preco * 0.1);
		} else
			if(quantidade > 20 && quantidade <= 50) {
				System.out.println("Você recebeu 20% de desconto!");
				return preco - (preco * 0.2);
			} else 
				if (quantidade > 50) {
					System.out.println("Você recebeu 25% de desconto!");
					return preco - (preco * 0.25);
				}
		System.out.println("Você não recebeu desconto!");
		return preco;
	}
}
