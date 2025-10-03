package app;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Materia {
    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private final int id;
    private final String nombre;
    private Profesor profesor; // Asociación: una Materia puede tener un Profesor asignado

    public Materia(String nombre) {
        this.id = SEQ.getAndIncrement();
        this.nombre = Objects.requireNonNull(nombre, "nombre");
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Optional<Profesor> getProfesor() { return Optional.ofNullable(profesor); }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    @Override public String toString() {
        return "Materia{" + id + ": " + nombre + (profesor != null ? ", Prof: " + profesor.getNombre() : "") + "}";
    }
}
