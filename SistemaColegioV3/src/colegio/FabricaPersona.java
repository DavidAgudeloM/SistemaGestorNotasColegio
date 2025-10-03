package colegio;

public class FabricaPersona {
    public static Persona crearEstudiante(String id, String nombre, String apellido, EstrategiaNota estrategia) {
        return new Estudiante(id, nombre, apellido, estrategia);
    }

    public static Persona crearProfesor(String id, String nombre, String apellido, String especialidad) {
        return new Profesor(id, nombre, apellido, especialidad);
    }
}
