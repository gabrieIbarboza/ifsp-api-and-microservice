package lista1.Exercicio23;

class Circulo extends FiguraGeometrica {
	 private double raio;

	 public Circulo(double raio) {
	     this.raio = raio;
	 }

	 @Override
	 public double calcularArea() {
	     return Math.PI * raio * raio;
	 }
}
