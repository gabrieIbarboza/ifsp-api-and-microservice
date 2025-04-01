package lista2.exercicio19;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número entre 0.00 e 100.00: ");
        double valor = scanner.nextDouble();
        
        if (valor < 0 || valor > 100) {
            System.out.println("Valor fora do intervalo permitido!");
        } else {
            System.out.println(converterParaExtenso(valor));
        }
        
        scanner.close();
    }
    
    public static String converterParaExtenso(double valor) {
        String[] unidades = {"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
        String[] especiais = {"dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
        String[] dezenas = {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
        String[] centenas = {"", "cem"};
        
        int reais = (int) valor;
        int centavos = (int) Math.round((valor - reais) * 100);
        
        String extenso = "";
        
        if (reais == 100) {
            extenso = "cem reais";
        } else if (reais >= 20) {
            extenso = dezenas[reais / 10];
            if (reais % 10 != 0) {
                extenso += " e " + unidades[reais % 10];
            }
        } else if (reais >= 10) {
            extenso = especiais[reais - 10];
        } else if (reais > 0) {
            extenso = unidades[reais];
        }
        
        if (reais == 1) {
            extenso += " real";
        } else if (reais > 1) {
            extenso += " reais";
        }
        
        if (centavos > 0) {
            if (!extenso.isEmpty()) {
                extenso += " e ";
            }
            if (centavos >= 20) {
                extenso += dezenas[centavos / 10];
                if (centavos % 10 != 0) {
                    extenso += " e " + unidades[centavos % 10];
                }
            } else if (centavos >= 10) {
                extenso += especiais[centavos - 10];
            } else {
                extenso += unidades[centavos];
            }
            extenso += " centavo" + (centavos > 1 ? "s" : "");
        }
        
        return extenso.isEmpty() ? "zero real" : extenso;
    }
}
