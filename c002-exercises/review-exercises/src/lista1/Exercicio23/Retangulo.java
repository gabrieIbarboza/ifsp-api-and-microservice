package lista1.Exercicio23;

class Retangulo extends FiguraGeometrica {
	 private double largura, altura;

	 public Retangulo(double largura, double altura) {
	     this.largura = largura;
	     this.altura = altura;
	 }

	 @Override
	 public double calcularArea() {
	     return largura * altura;
	 }
}
