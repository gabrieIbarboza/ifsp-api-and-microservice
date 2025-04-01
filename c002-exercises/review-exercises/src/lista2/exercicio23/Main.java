package lista2.exercicio23;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double pv, j, fv;
		int n;
		
		System.out.println("\nCapital Inicial: ");
		pv = input.nextDouble();
		System.out.println("\nTaxa de juros: ");
		j = input.nextDouble(); 
		System.out.println("\nPeríodo da aplicacao: ");
		n = input.nextInt();
		System.out.println("\nCalculando capital futuro......");
		
		fv = pv * Math.pow(1 + j, n);
		System.out.println("\nCapital futuro: "+fv);
		
	}
}
