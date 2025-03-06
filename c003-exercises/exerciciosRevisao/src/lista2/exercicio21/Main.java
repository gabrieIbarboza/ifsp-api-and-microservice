package lista2.exercicio21;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Digite um número: ");
        Double a = teclado.nextDouble();

        System.out.println("Digite um outro número: ");
        Double b = teclado.nextDouble();

        System.out.println("Digite um terceiro número: ");
        Double c = teclado.nextDouble();

        Double delta = Math.pow(b, 2) - 4 * a * c;
        System.out.println("O delta é: " + delta);

        if (delta < 0) {
            System.out.println("A equação não possui raízes reais.");
        } else {
            Double raiz1 = (-b + Math.sqrt(delta)) / (2 * a);
            Double raiz2 = (-b - Math.sqrt(delta)) / (2 * a);
            
            System.out.println("As raízes são: " + raiz1 + " e " + raiz2);
        }

        teclado.close();
    }
}
