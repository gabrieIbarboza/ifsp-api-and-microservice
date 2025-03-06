package lista1.Exercicio6;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		double numero = 0;
		
		System.out.println("Digite um número: ");
		numero = scanner.nextInt();
		
		int casas = 1;
		
		while((numero = numero / 10.0) > 1){
			casas++;
		}
		
		System.out.println(casas);
		scanner.close();
	}
}
