package lista2.exercicio4;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite um número: ");
		int numero = teclado.nextInt();
		
		if(numero > 100) {
			System.out.println("Este número é maior do que 100!");
		} else {
			System.out.println("Este número é menor ou igual a 100!");
		}
		
		teclado.close();
	}
}