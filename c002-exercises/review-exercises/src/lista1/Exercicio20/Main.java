package lista1.Exercicio20;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in);
		  Agenda lista = new Agenda();
		  String continuar = "s";
		  
		do {
			System.out.println("O que deseija fazer?");
			System.out.println("1 - cadastrar contato");
			System.out.println("2 - visualizar lista de contatos");
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1: 
				cadastrarContato(lista);
				break;
			case 2: 
				visualizarLista(lista);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
			scanner.nextLine();
			System.out.println("Deseja voltar ao menu incial? (s/n)");
			continuar = scanner.nextLine();
			
		} while (continuar.equalsIgnoreCase("s"));
		  
	}
	
	public static void cadastrarContato(Agenda lista) {
		String nome, numero, continuar = "s";
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.println("\n\nNome: ");
			nome = scanner.nextLine();
			System.out.println("Número: (coloque no formato (xx) xxxxx-xxxx )");
			numero = scanner.nextLine();
			Contato contato = new Contato(nome, numero);
			lista.addContato(contato);
			System.out.println("Contato adicionado com sucesso!");
			System.out.println("\nDeseja adicionar mais um contato? (s/n)");
			continuar = scanner.nextLine();
			
		} while (continuar.equalsIgnoreCase("s"));
	}
	
	public static void visualizarLista(Agenda lista) {
		System.out.println("\n\n----------Lista de contatos-----------");
		System.out.println("Quantidade:"+lista.getQtd()+"\n");
		lista.visualizarLista();
	}
}
