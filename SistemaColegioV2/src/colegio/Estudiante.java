package colegio;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase Estudiante que usa una EstrategiaNota para calcular promedio.
 */
public class Estudiante extends Persona {
    private List<Double> notas = new ArrayList<>();
    private EstrategiaNota estrategia; // Strategy

    public Estudiante(String id, String nombre, String apellido, EstrategiaNota estrategia) {
        super(id, nombre, apellido);
        this.estrategia = estrategia;
    }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public List<Double> getNotas() { return notas; }

    public double calcularNotaFinal() {
        return estrategia.calcular(notas);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " - Nota final: " + calcularNotaFinal();
    }
}
