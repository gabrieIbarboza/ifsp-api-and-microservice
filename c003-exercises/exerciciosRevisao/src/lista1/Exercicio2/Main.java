package lista1.Exercicio2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double x1, y1, z1, x2, y2, z2 = 0;
		
		System.out.print("Digite as coordenadas do primeiro ponto (x1 y1 z1): ");
        x1 = scanner.nextDouble();
        y1 = scanner.nextDouble();
        z1 = scanner.nextDouble();
        
        System.out.print("\nDigite as coordenadas do segundo ponto (x2 y2 z2): ");
        x2 = scanner.nextDouble();
        y2 = scanner.nextDouble();
        z2 = scanner.nextDouble();
        
        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
        System.out.println("A distância entre os pontos é: " + distancia);
        
        scanner.close();
	}
}
