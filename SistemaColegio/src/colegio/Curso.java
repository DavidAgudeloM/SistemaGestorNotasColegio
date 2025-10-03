package colegio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Curso contiene varios estudiantes y un profesor (agregación).
 */
public class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    public void asignarProfesor(Profesor p) { this.profesor = p; }
    public Profesor getProfesor() { return profesor; }

    public void agregarEstudiante(Estudiante e) { estudiantes.add(e); }

    public List<Estudiante> getEstudiantes() { return estudiantes; }

    // Uso de Streams: calcular promedio del curso usando lambdas
    public double promedioCurso() {
        return estudiantes.stream()
                .mapToDouble(Estudiante::promedio)
                .average()
                .orElse(0.0);
    }

    // Filtrar estudiantes cuyo promedio sea mayor a un umbral
    public List<Estudiante> estudiantesConPromedioMayorQue(double umbral) {
        return estudiantes.stream()
                .filter(s -> s.promedio() > umbral)
                .collect(Collectors.toList());
    }
}
