package colegio;

/**
 * Patrón Factory Method para crear Personas (Estudiante o Profesor)
 */
public class FabricaPersona {
    public static Persona crearEstudiante(String id, String nombre, String apellido) {
        return new Estudiante(id, nombre, apellido);
    }

    public static Persona crearProfesor(String id, String nombre, String apellido, String especialidad) {
        return new Profesor(id, nombre, apellido, especialidad);
    }
}
