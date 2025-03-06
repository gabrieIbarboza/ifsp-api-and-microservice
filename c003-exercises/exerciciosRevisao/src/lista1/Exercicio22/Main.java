package lista1.Exercicio22;

public class Main {
	public static void main(String[] args) {
        Pessoa avoPaterno = new Pessoa("José", 80, null, null);
        Pessoa avoMaterno = new Pessoa("Maria", 78, null, null);
        Pessoa avoMaterno2 = new Pessoa("Antonio", 79, null, null);
        Pessoa avoPaterno2 = new Pessoa("Ana", 75, null, null);

        Pessoa pai = new Pessoa("Carlos", 50, avoPaterno, avoPaterno2);
        Pessoa mae = new Pessoa("Fernanda", 48, avoMaterno, avoMaterno2);
        
        Pessoa filho = new Pessoa("Lucas", 25, pai, mae);

        filho.exibirArvore();
        pai.exibirArvore();
        mae.exibirArvore();
        avoPaterno.exibirArvore();
        avoPaterno2.exibirArvore();
        avoMaterno.exibirArvore();
        avoMaterno2.exibirArvore();
    }
}
