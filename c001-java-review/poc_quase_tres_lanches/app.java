package poc_quase_tres_lanches;

import java.util.Scanner;

public class app {
	
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		//System.out.println("Hello World");
		//Prato prato = new Pizza(enumMolho.VERMELHO, enumRecheio.CALABRESA_FUTURO, enumBorda.CATUPIRY);
		
		Pedido pedido = criarPedido();

        while (true) {
            System.out.printf("\n===== MENU %s =====\n", pedido.getNomeCliente());
            System.out.println("1. Iniciar Novo Pedido");
            System.out.println("2. Adicionar Item ao Pedido");
            System.out.println("3. Emitir Nota Fiscal");
            System.out.println("4. Calcular Troco");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha;
            try {
                escolha = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer
                continue;
            }

            switch (escolha) {
                case 1:
                	pedido = criarPedido();
                    break;
                case 2:
                    System.out.println("Você escolheu a Opção 2.");
                    break;
                case 3:
                    System.out.println("Você escolheu a Opção 3.");
                    break;
                case 4:
                    System.out.println("Você escolheu a Opção 4.");
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
	}
	
	private static Pedido criarPedido() {
		Pedido pedido = new Pedido();
		
    	System.out.print("Informe o nome do cliente: ");
    	String input = scanner.next();
    	scanner.nextLine();
    	
    	pedido.setNomeCliente(input);
    	return pedido;
	}

}
