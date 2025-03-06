package lista1.Exercicio18;

import java.util.Scanner;

public class Main {
    private static final String[] UNIDADES = {
        "zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"
    };
    
    private static final String[] DEZENAS = {
        "", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"
    };

    private static final String[] ESPECIAIS = {
        "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"
    };

    private static final String[] CENTENAS = {
        "", "cem", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"
    };

    private static final String[] MILHARES = {
        "", "mil", "milhão", "bilhão"
    };

    public static void main(String[] args) {
        long numero = lerNumero(); 
        if (numero < 0 || numero > 999_999_999) {
            System.out.println("Número fora do intervalo permitido (0 a 999.999.999).");
        } else {
            String extenso = converterParaExtenso(numero);
            System.out.println("\nNúmero por extenso: " + extenso);
        }
    }

    public static long lerNumero() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número de até 9 dígitos: ");
        long numero = scanner.nextLong();
        scanner.close();
        return numero;
    }

    public static String converterParaExtenso(long numero) {
        if (numero == 0) return UNIDADES[0];

        String resultado = "";
        int grupo = 0;

        while (numero > 0) {
            int parte = (int) (numero % 1000);
            if (parte != 0) {
                String parteExtenso = traduzirCentena(parte);
                resultado = parteExtenso + (grupo > 0 ? " " + MILHARES[grupo] : "") + " " + resultado;
            }
            numero /= 1000;
            grupo++;
        }

        return resultado.trim();
    }

    public static String traduzirCentena(int numero) {
        if (numero == 100) return "cem"; 

        int centena = numero / 100;
        int dezena = (numero % 100) / 10;
        int unidade = numero % 10;

        String resultado = "";

        if (centena > 0) resultado += CENTENAS[centena];
        if (dezena == 1) {
            resultado += (centena > 0 ? " e " : "") + ESPECIAIS[unidade];
        } else {
            if (dezena > 0) resultado += (centena > 0 ? " e " : "") + DEZENAS[dezena];
            if (unidade > 0) resultado += (dezena > 0 ? " e " : "") + UNIDADES[unidade];
        }

        return resultado;
    }
}

