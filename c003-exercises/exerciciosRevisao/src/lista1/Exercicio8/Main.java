package lista1.Exercicio8;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite um número: ");
		int numero = scanner.nextInt();
		
		for(int i = 0; i < numero; i++) {
			System.out.print(fibonacci(i) + " ");
		}
		
		scanner.close();
	}
	
	public static int fibonacci(int numero) {
		if(numero == 0 || numero == 1) {
			return numero;
		} else {
			return fibonacci(numero - 1) + fibonacci(numero - 2);
		}
	}
	
	
}
