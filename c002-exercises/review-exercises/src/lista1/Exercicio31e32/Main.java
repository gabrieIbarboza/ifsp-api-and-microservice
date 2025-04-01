package lista1.Exercicio31e32;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\n--------- EXERCÍCIO 1 ----------\n");
		double a,b,c, delta = 0;
		while (true) {
			try {
				System.out.print("Digite o valor de a: ");
		        a = scanner.nextDouble();
		        if (a == 0) {
	                System.out.println("O valor de 'a' não pode ser zero");
	                continue;
	            }
	            break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
		while (true) {
			try {
				System.out.print("Digite o valor de b: ");
				b = scanner.nextDouble();
				break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
		while (true) {
			try {
				System.out.print("Digite o valor de c: ");
		        c = scanner.nextDouble();
				break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
        
        delta = Math.pow(b, 2) - 4 * a * c;
        
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("As raízes da equação são: " + x1 + " e " + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            System.out.println("A equação possui uma única raiz: " + x);
        } else {
            System.out.println("A equação não possui raízes reais.");
        }
        System.out.println("\n--------------------------------\n");
        
        
        System.out.println("\n--------- EXERCÍCIO 2 ----------\n");
        
        double x1, y1, z1, x2, y2, z2 = 0;
		System.out.print("\nDigite as coordenadas do primeiro ponto (x1 y1 z1): ");
		while (true) {
			try {
		        x1 = scanner.nextDouble();
		        y1 = scanner.nextDouble();
		        z1 = scanner.nextDouble();
		        break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
        System.out.print("\nDigite as coordenadas do segundo ponto (x2 y2 z2): ");
        while (true) {
			try {
		        x2 = scanner.nextDouble();
		        y2 = scanner.nextDouble();
		        z2 = scanner.nextDouble();
		        break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
        
        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
        System.out.println("\nA distância entre os pontos é: " + distancia);
        
        System.out.println("\n--------------------------------\n");
        
        
        System.out.println("\n--------- EXERCÍCIO 3 ----------\n");
        		
		System.out.println("Entre com as coordenadas x1:");
		while (true) {
			try {
				x1 = scanner.nextFloat();
				break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
		System.out.println("Entre com as coordenadas y1:");
		while (true) {
			try {
				y1 = scanner.nextFloat();
				break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
		System.out.println("Entre com as coordenadas x2:");
		while (true) {
			try {
				x2 = scanner.nextFloat();
				break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
		System.out.println("Entre com as coordenadas y2:");
		while (true) {
			try {
				y2 = scanner.nextFloat();
				break;
			} catch (InputMismatchException e) {
				 System.out.println("Entrada inválida! Por favor, insira um número válido.");
	             scanner.next();
			}
		}
		
		distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		System.out.println("A distância é: " + distancia);
        
        System.out.println("\n--------------------------------\n");
        
        scanner.close();
        
        //------------------------ EXERCÍCIO 32 --------------------------
        // exceções são problemas que você pode tratar para garantir
        // que o programa continue a rodar corretamente, enquanto os erros indicam
        // falhas críticas que o programa provavelmente não conseguirá recuperar.
	}
	
	
}
