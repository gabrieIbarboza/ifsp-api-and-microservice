package lista1.Exercicio3;

import java.util.Scanner;

public class Distancia {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		double x1, y1, x2, y2, distancia;
		
		System.out.println("Entre com as coordenadas x1:");
		x1 = teclado.nextFloat();
		System.out.println("Entre com as coordenadas y1:");
		y1 = teclado.nextFloat();
		System.out.println("Entre com as coordenadas x2:");
		x2 = teclado.nextFloat();
		System.out.println("Entre com as coordenadas y2:");
		y2 = teclado.nextFloat();
		
		distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		System.out.println("A distância é: " + distancia);
		
		teclado.close();
	}
}
