package com.cooprkc.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Cooperativa: administra el registro de socios.
 */
public class Cooperativa {
    private final String nombre;
    private final List<Socio> socios = new ArrayList<>();

    public Cooperativa(String nombre) {
        this.nombre = Objects.requireNonNull(nombre, "nombre");
    }

    public String getNombre() {
        return nombre;
    }

    public void registrarSocio(Socio socio) {
        socios.add(Objects.requireNonNull(socio));
    }

    public List<Socio> getSocios() {
        return Collections.unmodifiableList(socios);
    }
}
