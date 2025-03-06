package lista2.exercicio14;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite sua nota na prova 1: ");
		Double nota1 = teclado.nextDouble();
		
		System.out.println("Digite sua nota na prova 3: ");
		Double nota2 = teclado.nextDouble();
		
		System.out.println("Digite sua nota na prova 3: ");
		Double nota3 = teclado.nextDouble();
		
		Double total = nota1 + nota2 + nota3;
		Double media = total/3;
		
		System.out.println("\tMédia obtida: " + media);
		
		if(media >= 7) {
			System.out.println("Você foi aprovado!");
		} else
			if (media < 7 && media > 3){
				System.out.println("Você está de exame!");
			} else {
				System.out.println("Você foi reprovado!");
			}
		
	}
}
