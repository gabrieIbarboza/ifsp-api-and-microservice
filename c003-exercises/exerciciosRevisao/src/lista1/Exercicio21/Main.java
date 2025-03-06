package lista1.Exercicio21;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Livro> acervo = new ArrayList<>();
        gerarLivros(acervo);
        List<Livro> livrosSelecionados = new ArrayList<>();
        
        menuEscolhaLivro(livrosSelecionados, acervo, scanner);

        System.out.print("\nCliente: ");
        String cliente = scanner.nextLine();
        
        Date dataEmprestimo = new Date(System.currentTimeMillis());
        
        Emprestimo emprestimo = new Emprestimo(cliente, livrosSelecionados, dataEmprestimo);
        emprestimo.gerarRecibo();
        
        scanner.close();
    }

    private static void gerarLivros(List<Livro> acervo) {
        acervo.add(new Livro(1, "O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        acervo.add(new Livro(2, "Memórias Póstumas de Brás Cubas", "Machado de Assis"));
        acervo.add(new Livro(3, "A Hora da Estrela", "Clarice Lispector"));
    }
    
    private static void menuEscolhaLivro(List<Livro> livrosSelecionados, List<Livro> acervo, Scanner scanner) {
        String continuar;
        do {
            System.out.println("\nSelecione o livro desejado pelo código: ");
            for (Livro livro : acervo) {
                livro.exibirInformacoes();
            }

            boolean escolhaValida = false;
            while (!escolhaValida) {
                System.out.print("Digite o código do livro: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                
                for (Livro livro : acervo) {
                    if (livro.getCodigo() == opcao) {
                        livrosSelecionados.add(livro);
                        System.out.println("Livro '" + livro.getNomeLivro() + "' adicionado à sua lista!");
                        escolhaValida = true;
                        break;
                    }
                }
                
                if (!escolhaValida) {
                    System.out.println("Código inválido. Escolha um livro existente!");
                }
            }
            
            System.out.print("\nDeseja escolher mais um livro? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }
}
