package lista1.Exercicio7;

import java.util.Scanner;

public class Codigo { 
	public static void main(String[] args) { 
		Scanner teclado = new Scanner(System.in); 
		int codigo; 
		
		//-------- CÓDIGO A (CORRETO) ----------
//		System.out.println("Informe o código: "); 
//		codigo = teclado.nextInt(); 
//		
//		while (codigo != -1) { 
//			System.out.println("Código: " + codigo); 
//			System.out.println("Informe o código: "); 
//			codigo = teclado.nextInt(); 
//		} 
		
		//-------- CÓDIGO B (INCORRETO) ----------
		
		//o código B está incorreto pois em um loop DO WHILE o código será primero impresso e depois verificado se 
		//está errado. Sendo assim, é preciso colocar o IF para fazer a verificação do número.
		do { 
			System.out.print("Informe o código: "); 
			codigo = teclado.nextInt(); 
			if (codigo != -1) {
                System.out.println("Código: " + codigo);
            }
		} while (codigo != -1); 
	} 
}	