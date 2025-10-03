package colegio;

/**
 * Subclase Profesor hereda de Persona. Tiene una especialidad.
 */
public class Profesor extends Persona {
    private String especialidad;

    public Profesor(String id, String nombre, String apellido, String especialidad) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " - " + especialidad;
    }
}
