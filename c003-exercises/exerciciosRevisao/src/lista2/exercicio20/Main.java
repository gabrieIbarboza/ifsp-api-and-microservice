package lista2.exercicio20;
 
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Digite um número em metros: ");
        double metros = teclado.nextDouble();
        double pes = 3.281;
        
        if(metros > 0) {
        	double conversao = metros * pes;
            
        	System.out.printf("%.2f metros convertido para pés fica %.2f pés", metros, conversao);

        } else {
        	System.out.println("Você forneceu um número inválido!");
        }

        teclado.close();
    }
}
