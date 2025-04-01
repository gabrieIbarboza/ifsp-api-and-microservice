package lista2.exercicio3;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite um número: ");
		int numero = teclado.nextInt();
		
		if(numero > 50) {
			System.out.println("Este número é maior do que 50!");
		} else {
			System.out.println("Este número não é maior do que 50!");
		}
		
		teclado.close();
	}
}
