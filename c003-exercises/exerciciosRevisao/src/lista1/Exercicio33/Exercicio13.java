package lista1.Exercicio33;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

	public class Exercicio13 {
		public static void main(String[] args) {
			List<Integer> vetor = new ArrayList<Integer>();
			int atual;
			
			Random random = new Random(); 
			
	       for (int i = 0; i < 100; i++) {
	           vetor.add(random.nextInt(101)); 
	       }
	       
	       System.out.print("\n\n Vetor desordenado: \n");
	       for (int numero : vetor) {
	           System.out.print(numero + " ");
	       }
	       
	       for (int i = 0; i < vetor.size(); i++) {
	       	for (int j = 0; j < vetor.size() - 1 - i; j++) {
	               if(vetor.get(j) > vetor.get(j + 1)) {
	               	atual = vetor.get(j);
	               	vetor.set(j, vetor.get(j + 1));
	               	vetor.set(j + 1, atual);
	               }
	           }
	       }
	
	       System.out.print("\n\n Vetor ordenado: \n");
	       for (int numero : vetor) {
	           System.out.print(numero + " ");
       }
	}
}
