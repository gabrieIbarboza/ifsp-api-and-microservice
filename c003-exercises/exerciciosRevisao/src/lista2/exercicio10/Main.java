package lista2.exercicio10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        List<Integer> numerosOrdenados = new ArrayList<>();

        System.out.println("Digite um número: ");
        int numero1 = teclado.nextInt();

        System.out.println("Digite um outro número: ");
        int numero2 = teclado.nextInt();

        System.out.println("Digite um terceiro número: ");
        int numero3 = teclado.nextInt();

        numerosOrdenados.add(numero1);
        numerosOrdenados.add(numero2);
        numerosOrdenados.add(numero3);

        Collections.sort(numerosOrdenados);

        System.out.println("Os números em ordem crescente são: " + numerosOrdenados);

        teclado.close();
    }
}
