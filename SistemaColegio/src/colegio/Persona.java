package colegio;

/**
 * Clase base Persona: atributos comunes para Estudiante y Profesor.
 */
public abstract class Persona {
    protected String id;
    protected String nombre;
    protected String apellido;

    public Persona(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + id + ")";
    }
}
