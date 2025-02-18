# POC Quase Três Lanches

Este projeto é uma prova de conceito (POC) para a gestão de pedidos em uma lanchonete. A aplicação permite criar pedidos, adicionar diferentes tipos de pratos (pizzas, lanches e salgados), calcular preços e emitir notas fiscais.

## Estrutura do Projeto

### Classes Principais

- **Prato**: Classe abstrata que serve como base para todos os tipos de pratos. Contém atributos comuns como `dataValidade`, `peso` e `preco`, além de um método abstrato `calcularPreco()`.
- **Pizza**: Subclasse de `Prato` que representa uma pizza. Possui atributos específicos como `molho`, `recheio` e `borda`.
- **Lanche**: Subclasse de `Prato` que representa um lanche. Possui atributos específicos como `pao`, `recheio` e `molho`.
- **Salgado**: Subclasse de `Prato` que representa um salgado. Possui atributos específicos como `recheio`, `massa` e `tipo`.
- **Pedido**: Classe que representa um pedido feito por um cliente. Contém uma lista de itens consumidos (`Prato`), o nome do cliente e a taxa de serviço. Possui métodos para adicionar itens, emitir nota fiscal e calcular troco.

### Enums

- **enumBorda**: Define os tipos de borda para pizzas.
- **enumMassa**: Define os tipos de massa para salgados.
- **enumMolho**: Define os tipos de molho para pizzas e lanches.
- **enumPao**: Define os tipos de pão para lanches.
- **enumRecheio**: Define os tipos de recheio para pizzas, lanches e salgados.
- **enumTipo**: Define os tipos de preparo para salgados (frito ou assado).

## Funcionamento do Sistema

1. **Inicialização do Pedido**:
   - O sistema é iniciado pela classe `app`, que contém o método `main`.
   - Um novo pedido é criado chamando o método `criarPedido`, que solicita o nome do cliente e inicializa um objeto `Pedido`.

2. **Menu de Opções**:
   - O sistema exibe um menu com opções para iniciar um novo pedido, adicionar itens ao pedido, emitir nota fiscal, calcular troco ou sair do programa.
   - As opções são lidas do console e processadas em um loop `while`.

3. **Adição de Itens ao Pedido**:
   - Ao escolher a opção de adicionar item, o método `criarPrato` é chamado.
   - O usuário escolhe o tipo de prato (pizza, salgado ou lanche) e, em seguida, os atributos específicos são solicitados (molho, recheio, borda, etc.).
   - O prato é criado e adicionado ao pedido usando o método `adicionarItem` da classe `Pedido`.

4. **Emissão de Nota Fiscal**:
   - Ao escolher a opção de emitir nota fiscal, o método `emitirNotaFiscal` da classe `Pedido` é chamado.
   - O método calcula o total do pedido, incluindo a taxa de serviço, e exibe uma nota fiscal detalhada com os itens consumidos e seus preços.

5. **Cálculo de Troco**:
   - Ao escolher a opção de calcular troco, o método `calcularTroco` da classe `Pedido` é chamado.
   - O usuário informa o valor recebido e o sistema calcula e exibe o troco a ser devolvido.

## Exemplo de Uso

```java
public class App {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("Cliente 1");
        
        Pizza pizza = new Pizza(enumMolho.TOMATE, enumRecheio.QUEIJO, enumBorda.CATUPIRY);
        pizza.calcularPreco();
        pedido.adicionarItem(pizza);
        
        Lanche lanche = new Lanche(enumPao.BRANCO, enumRecheio.PRESUNTO, enumMolho.BRANCO);
        lanche.calcularPreco();
        pedido.adicionarItem(lanche);
        
        pedido.emitirNotaFiscal();
    }
}
```