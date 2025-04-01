package lista1.Exercicio23;

class Quadrado extends FiguraGeometrica {
	 private double lado;

	 public Quadrado(double lado) {
	     this.lado = lado;
	 }

	 @Override
	 public double calcularArea() {
	     return lado * lado;
	 }
}
