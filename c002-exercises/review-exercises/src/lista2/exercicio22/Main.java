package lista2.exercicio22;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		double p0, v, a, pf;
		int t;
		
		System.out.println("\nPosição inicial: ");
		p0 = input.nextDouble();
		System.out.println("\nVelocidade: ");
		v = input.nextDouble(); 
		System.out.println("\nAceleração: ");
		a = input.nextDouble();
		System.out.println("\nTempo: ");
		t = input.nextInt();
		
		System.out.println("\ncalculando...");
		Double aux = Math.pow(t, 2);
		pf = p0 + v * t + (a * aux)/2;
		System.out.println("Ponto final: "+pf);
		
		input.close();
	}
}
