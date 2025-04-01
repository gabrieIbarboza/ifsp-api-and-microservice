package lista1.Exercicio12;

import java.util.Scanner;

//Leia	uma	matriz	3	x	3	que	representa	um	tabuleiro	de	jogo	da	velha	e	indique	qual	posição	
		// deveria	ser	jogada	para	ganhar	o	jogo	(se	possível)	ou	ao	menos	para	evitar	uma	derrota.
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];
        
        // Leitura do tabuleiro
        System.out.println("Digite o tabuleiro (3x3), usando X, O e '_' para espaços vazios:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = sc.next().charAt(0);
            }
        }
        
        System.out.println("Tabuleiro atual:");
        exibirTabuleiro(tabuleiro);
        
        String jogada = melhorJogada(tabuleiro);
        System.out.println(jogada);
	}
	
	public static void exibirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checarVitoria(char[][] tabuleiro, char jogador) {
        // Verifica linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) || // linha
                (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador)) { // coluna
                return true;
            }
        }
        if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) || // diagonal principal
            (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) { // diagonal inversa
            return true;
        }
        return false;
    }

    public static String melhorJogada(char[][] tabuleiro) {
        // Verifica se o jogador 'O' pode ganhar ou bloquear
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '_') {
                    // Tenta colocar 'O' para ganhar
                    tabuleiro[i][j] = 'O';
                    if (checarVitoria(tabuleiro, 'O')) {
                        return "Jogada vencedora: linha " + (i+1) + ", coluna " + (j+1);
                    }
                    tabuleiro[i][j] = ' '; // Desfaz a jogada

                    // Tenta bloquear o jogador 'X' de ganhar
                    tabuleiro[i][j] = 'X';
                    if (checarVitoria(tabuleiro, 'X')) {
                        tabuleiro[i][j] = 'O'; // Bloqueia o 'X'
                        return "Bloquear jogada de 'X' na linha " + (i+1) + ", coluna " + (j+1);
                    }
                    tabuleiro[i][j] = ' '; // Desfaz a jogada
                }
            }
        }
        
        return "Não há jogada imediata para ganhar ou bloquear.";
    }
}
