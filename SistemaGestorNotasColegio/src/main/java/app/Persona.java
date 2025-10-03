package app;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Persona {
    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private final int id;
    private final String nombre;

    protected Persona(String nombre) {
        this.id = SEQ.getAndIncrement();
        this.nombre = Objects.requireNonNull(nombre, "nombre");
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override public String toString() { return getClass().getSimpleName() + "{" + id + ": " + nombre + "}"; }
}
