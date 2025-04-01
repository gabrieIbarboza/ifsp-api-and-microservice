package lista1.Exercicio14;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		 int[] vetor1 = new int[100];
		 int[] vetor2 = new int[100];
		 int[] vetor3 = new int[200];
		
		
		Random random = new Random(); 
		
       for (int i = 0; i < vetor1.length; i++) {
           vetor1[i] = random.nextInt(101); 
       }
       
       for (int i = 0; i < vetor2.length; i++) {
           vetor2[i] = random.nextInt(101); 
       }
       
       ordenar(vetor1);
       
       System.out.print("\n\n Vetor1 ordenado: \n");
       for (int numero : vetor1) {
           System.out.print(numero + " ");
       }
       ordenar(vetor2);
       System.out.print("\n\n Vetor2 ordenado: \n");
       for (int numero : vetor2) {
           System.out.print(numero + " ");
       }
       
       combinarEordenar(vetor1, vetor2, vetor3);
       System.out.print("\n\n Vetor3 ordenado: \n");
       for (int numero : vetor3) {
           System.out.print(numero + " ");
       }
      
	}
	
	public static void ordenar(int vetor[]) {
		int atual;
		 for (int i = 0; i < vetor.length; i++) {
	       	for (int j = 0; j < vetor.length - 1 - i; j++) {
               if(vetor[j] > vetor[j + 1]) {
	               	atual = vetor[j];
	               	vetor[j] = vetor[j + 1];
	               	vetor[j + 1] = atual;
               }
	        }
	     }
	}
	
	public static void combinarEordenar(int vetor1[], int vetor2[], int vetor3[]) {
		int i = 0, j = 0, k = 0;

        while (i < vetor1.length && j < vetor2.length) {
            if (vetor1[i] <= vetor2[j]) {
                vetor3[k++] = vetor1[i++];
            } else {
                vetor3[k++] = vetor2[j++];
            }
        }

        while (i < vetor1.length) {
            vetor3[k++] = vetor1[i++];
        }

        while (j < vetor2.length) {
            vetor3[k++] = vetor2[j++];
        }
    }
	
}
