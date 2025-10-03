package colegio;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase Estudiante hereda de Persona. Mantiene una lista de notas.
 */
public class Estudiante extends Persona {
    private List<Double> notas = new ArrayList<>();

    public Estudiante(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public List<Double> getNotas() { return notas; }

    public double promedio() {
        return notas.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
