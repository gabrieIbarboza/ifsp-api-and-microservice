package poc_quase_tres_lanches;

import java.time.LocalDate;
import java.util.Scanner;

public class app {
	
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
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
                	Prato prato = criarPrato();
                	if(prato != null) {
                		pedido.adicionarItem(prato);
                	}
                	else {
                		System.out.println("Nenhum item foi adicionado ao pedido!");                		
                	}
                    break;
                case 3:
                    pedido.emitirNotaFiscal();
                    scanner.nextLine();
                    break;
                case 4:
                	System.out.print("Informe o valor recebido: ");
                	Double dinheiroRecebido = scanner.nextDouble();
                    pedido.calcularTroco(dinheiroRecebido);
                    scanner.nextLine();
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
	
	private static Prato criarPrato() {
        System.out.println("Escolha o tipo de prato (1 - Pizza, 2 - Salgado, 3 - Lanche): ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
            	enumMolho molho = solicitarEnum(enumMolho.class, "Escolha o molho (VERMELHO, BRANCO, PESTO, TERIYAKI, BOLONHESA): ");
                enumRecheio recheio = solicitarEnum(enumRecheio.class, "Escolha o recheio (BROCOLIS, CARNE_SOJA, VEGARELA, JACA, CALABRESA_FUTURO): ");
                enumBorda borda = solicitarEnum(enumBorda.class, "Escolha a borda (SEM_BORDA, CATUPIRY, CHEDDER, CHOCOLATE, CHOCOLATE_BRANCO): ");

                Pizza pizza = new Pizza(molho, recheio, borda);
                pizza.setDataValidade(LocalDate.now().plusDays(3));
                pizza.setPeso(0.5);
                pizza.calcularPreco();
                return pizza;

            case 2:
            	enumRecheio recheioS = solicitarEnum(enumRecheio.class, "Escolha o recheio (BROCOLIS, CARNE_SOJA, VEGARELA, JACA, CALABRESA_FUTURO): ");
                enumMassa massa = solicitarEnum(enumMassa.class, "Escolha a massa (BRANCA, INTEGRAL): ");
                enumTipo tipo = solicitarEnum(enumTipo.class, "Escolha o tipo (FRITO, ASSADO): ");

                Salgado salgado = new Salgado(recheioS, massa, tipo);
                salgado.setDataValidade(LocalDate.now().plusDays(2));
                salgado.setPeso(0.3);
                salgado.calcularPreco();
                return salgado;

            case 3:
            	enumPao pao = solicitarEnum(enumPao.class, "Escolha o pão (ITALIANO, QUEIJO_3, PARMESAO, GRAOS_9, AUSTRALIANO): ");
                enumRecheio recheioL = solicitarEnum(enumRecheio.class, "Escolha o recheio (BROCOLIS, CARNE_SOJA, VEGARELA, JACA, CALABRESA_FUTURO): ");
                enumMolho molhoL = solicitarEnum(enumMolho.class, "Escolha o molho (VERMELHO, BRANCO, PESTO, TERIYAKI, BOLONHESA): ");

                Lanche lanche = new Lanche(pao, recheioL, molhoL);
                lanche.setDataValidade(LocalDate.now().plusDays(4));
                lanche.setPeso(0.4);
                lanche.calcularPreco();
                return lanche;

            default:
                System.out.println("Opção inválida!");
                return null;
        }
    }
	
	private static <E extends Enum<E>> E solicitarEnum(Class<E> enumClass, String mensagem) {
        E resultado = null;
        while (resultado == null) {
            System.out.println(mensagem);
            String entrada = scanner.nextLine().toUpperCase().replace(" ", "_");
            for (E valor : enumClass.getEnumConstants()) {
                if (valor.name().startsWith(entrada) || valor.name().equalsIgnoreCase(entrada)) {
                    resultado = valor;
                    break;
                }
            }
            if(resultado == null) {
            	System.out.println("Opção inválida. Tente novamente.");
            }
        }
        return resultado;
    }

}
