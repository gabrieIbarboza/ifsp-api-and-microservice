package lista1.Exercicio13;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		 int[] vetor = new int[100];
		int atual;
		
		Random random = new Random(); 
		
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(101); 
        }
        
        System.out.print("\n\n Vetor desordenado: \n");
        for (int numero : vetor) {
            System.out.print(numero + " ");
        }
        
        for (int i = 0; i < vetor.length; i++) {
        	for (int j = 0; j < vetor.length - 1 - i; j++) {
                if(vetor[j] > vetor[j + 1]) {
                	atual = vetor[j];
                	vetor[j] = vetor[j + 1];
                	vetor[j + 1] = atual;
                }
            }
        }

        System.out.print("\n\n Vetor ordenado: \n");
        for (int numero : vetor) {
            System.out.print(numero + " ");
        }
	}
}
