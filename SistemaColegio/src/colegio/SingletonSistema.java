package colegio;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton que administra los cursos, estudiantes y profesores.
 */
public class SingletonSistema {
    private static SingletonSistema instancia;

    private List<Curso> cursos = new ArrayList<>();
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Profesor> profesores = new ArrayList<>();

    private SingletonSistema() {}

    // Método synchronized para seguridad en hilos (simple)
    public static synchronized SingletonSistema getInstancia() {
        if (instancia == null) {
            instancia = new SingletonSistema();
        }
        return instancia;
    }

    public void agregarCurso(Curso c) { cursos.add(c); }
    public void agregarEstudiante(Estudiante e) { estudiantes.add(e); }
    public void agregarProfesor(Profesor p) { profesores.add(p); }

    public List<Curso> getCursos() { return cursos; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Profesor> getProfesores() { return profesores; }
}
