package lista1.Exercicio34;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercicio17 {
    private static final Map<Integer, String> romanos = new HashMap<>();

    static {
        romanos.put(1, "I"); romanos.put(4, "IV"); romanos.put(5, "V"); romanos.put(9, "IX");
        romanos.put(10, "X"); romanos.put(40, "XL"); romanos.put(50, "L"); romanos.put(90, "XC");
        romanos.put(100, "C"); romanos.put(400, "CD"); romanos.put(500, "D"); romanos.put(900, "CM");
    }

    public static void main(String[] args) {
        int numero = lerNumero();
        if (numero < 1 || numero > 999) {
            System.out.println("Número fora do intervalo permitido (1 a 999).");
        } else {
            String romano = converterParaRomano(numero);
            exibirResultado(numero, romano);
        }
    }

    public static int lerNumero() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número decimal (1 a 999): ");
        int numero = scanner.nextInt();
        scanner.close();
        return numero;
    }

    public static String converterParaRomano(int numero) {
        int[] valores = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder romano = new StringBuilder();

        for (int valor : valores) {
            while (numero >= valor) {
                romano.append(romanos.get(valor));
                numero -= valor;
            }
        }
        return romano.toString();
    }

    public static void exibirResultado(int numero, String romano) {
        System.out.println("O número " + numero + " em algarismos romanos é: " + romano);
    }
}
