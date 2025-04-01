package lista1.Exercicio11;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num, raiz = 0;
		int impar = 1;
		
		System.out.println("Insira um número inteiro para calcular a raiz quadrada: ");
		num = scanner.nextInt();
		
		while(true) {
			num = num - impar;
			if(num >= 0) {
				raiz++;
				impar += 2;
			}else {
				break;
			}
		}
		
		System.out.println("A raiz quadrada é, aproximadamente: "+raiz);
		
		scanner.close();
	}
}
