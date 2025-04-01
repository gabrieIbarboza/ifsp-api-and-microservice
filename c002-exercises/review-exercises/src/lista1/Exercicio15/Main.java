package lista1.Exercicio15;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int nota1, nota2, nota3, media;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Nota 1: ");
		nota1 = scanner.nextInt();
		System.out.println("\nNota 2: ");
		nota2 = scanner.nextInt();
		System.out.println("\nNota 3: ");
		nota3 = scanner.nextInt();
		
		media = calcularMedia(nota1, nota2, nota3);
		status(media);
	}
	
	public static int calcularMedia(int n1, int n2, int n3) {
		int media = (n1 + n2 + n3) / 3;
		return media;
	}
	
	public static void status(int media) {
		if(media >= 6) {
			System.out.println("\nAprovado");
		} else if(media < 6 && media >= 4) {
			System.out.println("\nVerificação	Suplementar");
		} else if(media < 4) {
			System.out.println("\nReprovado");
		}
	}
}
