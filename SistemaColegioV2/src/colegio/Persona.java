package colegio;

/**
 * Clase abstracta Persona que implementa la interfaz IPersona.
 */
public abstract class Persona implements IPersona {
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
    public String getDescripcion() {
        return nombre + " " + apellido + " (" + id + ")";
    }
}
