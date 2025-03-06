package lista2.exercicio9;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite um número: ");
		int numero1 = teclado.nextInt();
		
		System.out.println("Digite um outro número: ");
		int numero2 = teclado.nextInt();
		
		System.out.println("Digite um terceiro número: ");
		int numero3 = teclado.nextInt();
		
		if(numero1 > numero2 && numero1 > numero3) {
			System.out.println("O maior número é " + numero1);
		} else 
			if(numero2 > numero3 && numero2 > numero1) {
				System.out.println("O maior número é " + numero2);
			} else 
				if(numero3 > numero2 && numero3 > numero1) {
					System.out.println("O maior número é " + numero3);
				} else
					if(numero3 == numero2 && numero3 == numero1 && numero1 == numero2) {
						System.out.println("Os números são iguais!");
					}
		
		teclado.close();
	}
}