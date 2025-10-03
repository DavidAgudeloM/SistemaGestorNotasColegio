package app;

import java.util.*;
import java.util.stream.Collectors;

public class Estudiante extends Persona {
    // Mapa Materia -> lista de notas (composición: Estudiante "posee" notas por materia)
    private final Map<Materia, List<Nota>> notasPorMateria = new HashMap<>();

    public Estudiante(String nombre) { super(nombre); }

    public void registrarNota(Materia materia, Nota nota) {
        // Validaciones de reglas de negocio
        if (nota.getValor() < 0 || nota.getValor() > 5) throw new IllegalArgumentException("La nota debe estar entre 0 y 5");
        if (nota.getPeso() < 0 || nota.getPeso() > 100) throw new IllegalArgumentException("El peso debe estar entre 0 y 100");
        notasPorMateria.computeIfAbsent(materia, k -> new ArrayList<>()).add(nota);
    }

    public double calcularPromedio(Materia materia) {
        var repo = Repositorio.getInstance();
        var notas = notasPorMateria.getOrDefault(materia, List.of());
        if (notas.isEmpty()) return Double.NaN;
        return repo.getEstrategiaPromedio().calcular(notas);
    }

    public double promedioGeneral() {
        var repo = Repositorio.getInstance();
        var proms = notasPorMateria.values().stream()
                .filter(lst -> !lst.isEmpty())
                .map(repo.getEstrategiaPromedio()::calcular)
                .filter(v -> !Double.isNaN(v))
                .mapToDouble(Double::doubleValue)
                .average();
        return proms.isPresent() ? proms.getAsDouble() : Double.NaN;
    }

    public Map<Materia, List<Nota>> getNotasPorMateria() { return notasPorMateria; }
}
