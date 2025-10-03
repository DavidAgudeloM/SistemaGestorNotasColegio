package app;

public class Nota {
    private final double valor; // 0.0 a 5.0
    private final double peso;  // 0 a 100 (solo usado por PromedioPonderado)

    public Nota(double valor, double peso) {
        this.valor = valor;
        this.peso = peso;
    }

    public double getValor() { return valor; }
    public double getPeso() { return peso; }
}
