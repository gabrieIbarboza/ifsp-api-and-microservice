package lista1.Exercicio33;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercicio14 {
	public static void main(String[] args) {
		List<Integer> vetor1 = new ArrayList<Integer>();
		List<Integer> vetor2 = new ArrayList<Integer>();
		List<Integer> vetor3 = new ArrayList<Integer>();
		
		
		Random random = new Random(); 
		
      for (int i = 0; i < 100; i++) {
    	  vetor1.add(random.nextInt(101)); 
      }
      
      for (int i = 0; i < 100; i++) {
    	  vetor2.add(random.nextInt(101)); 
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
	
	public static void ordenar(List<Integer> vetor) {
		int atual;
		 for (int i = 0; i < vetor.size(); i++) {
	       	for (int j = 0; j < vetor.size() - 1 - i; j++) {
              if(vetor.get(j) > vetor.get(j + 1)) {
	               	atual = vetor.get(j);
	               	vetor.set(j, vetor.get(j + 1));
	               	vetor.set(j + 1, atual);
              }
	        }
	     }
	}
	
	public static void combinarEordenar(List<Integer> vetor1, List<Integer> vetor2, List<Integer> vetor3) {
	    int i = 0, j = 0;

	    while (i < vetor1.size() && j < vetor2.size()) {
	        if (vetor1.get(i) <= vetor2.get(j)) {
	            vetor3.add(vetor1.get(i++));
	        } else {
	            vetor3.add(vetor2.get(j++));
	        }
	    }

	    while (i < vetor1.size()) {
	        vetor3.add(vetor1.get(i++));
	    }

	    while (j < vetor2.size()) {
	        vetor3.add(vetor2.get(j++));
	    }
	}


}
