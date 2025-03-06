package lista1.Exercicio16;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int tempoTotal = lerTempo(); 
        int horas = calcularHoras(tempoTotal); 
        int minutos = calcularMinutos(tempoTotal); 
        int segundos = calcularSegundos(tempoTotal); 
        exibirResultado(horas, minutos, segundos);
	}
	
	public static int lerTempo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o tempo em segundos: ");
        int tempo = scanner.nextInt();
        scanner.close();
        return tempo;
    }
	
	public static int calcularHoras(int segundos) {
		int horas = segundos / 3600;
		return horas;
	}
	
	public static int calcularMinutos(int segundos) {
		int minutos = (segundos % 3600) / 60;
		return minutos;
	}
	
	public static int calcularSegundos(int segundos) {
		return segundos % 60;
	}
	
	public static void exibirResultado(int horas, int minutos, int segundos) {
		System.out.println("Tempo convertido: " + horas + "h " + minutos + "m " + segundos + "s");
	}
}
