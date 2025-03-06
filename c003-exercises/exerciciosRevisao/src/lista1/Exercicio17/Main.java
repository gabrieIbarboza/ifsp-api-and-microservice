package lista1.Exercicio17;

import java.util.Scanner;

public class Main {
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
        String[] unidades = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] dezenas = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] centenas = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

        int centena = numero / 100;
        int dezena = (numero % 100) / 10;
        int unidade = numero % 10;

        return centenas[centena] + dezenas[dezena] + unidades[unidade];
    }

    public static void exibirResultado(int numero, String romano) {
        System.out.println("O número " + numero + " em algarismos romanos é: " + romano);
    }
}
