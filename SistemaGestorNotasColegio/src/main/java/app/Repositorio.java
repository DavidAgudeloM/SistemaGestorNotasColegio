package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repositorio {
    private static final Repositorio INSTANCE = new Repositorio();

    private final List<Estudiante> estudiantes = new ArrayList<>();
    private final List<Profesor> profesores = new ArrayList<>();
    private final List<Materia> materias = new ArrayList<>();
    private PromedioStrategy estrategiaPromedio = new PromedioSimple(); // Strategy por defecto

    private Repositorio() {}
    public static Repositorio getInstance() { return INSTANCE; }

    // CRUD muy básicos (en memoria)
    public void guardarEstudiante(Estudiante e) { estudiantes.add(e); }
    public void guardarProfesor(Profesor p) { profesores.add(p); }
    public void guardarMateria(Materia m) { materias.add(m); }

    public Optional<Estudiante> buscarEstudiante(int id) {
        return estudiantes.stream().filter(e -> e.getId() == id).findFirst();
    }
    public Optional<Materia> buscarMateria(int id) {
        return materias.stream().filter(m -> m.getId() == id).findFirst();
    }

    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Profesor> getProfesores() { return profesores; }
    public List<Materia> getMaterias() { return materias; }

    public PromedioStrategy getEstrategiaPromedio() { return estrategiaPromedio; }
    public void setEstrategiaPromedio(PromedioStrategy estrategia) { this.estrategiaPromedio = estrategia; }
}
