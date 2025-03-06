package lista1.Exercicio10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double saldo = 0, investMensal, juros;
        int ano = 0;
        String continuar = "s";
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nQuanto dinheiro será investido mensalmente? ");
        investMensal = input.nextDouble();
        System.out.println("\nQual será a porcentagem de juros? ");
        juros = input.nextDouble() / 100;
        input.nextLine();  

        do {
            ano++;
            for (int i = 0; i < 12; i++) {
                saldo += investMensal;
                saldo += saldo * juros; 
            }
            
            System.out.println("\nSaldo do investimento após " + ano + " ano(s): " + saldo);
            System.out.println("\n\nDeseja investir mais um ano? (s/n)");
            continuar = input.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        input.close(); 
    }
}
