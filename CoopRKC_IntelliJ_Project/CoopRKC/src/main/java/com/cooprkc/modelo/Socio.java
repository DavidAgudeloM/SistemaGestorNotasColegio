package com.cooprkc.modelo;

import com.cooprkc.excepciones.CuentaDuplicadaException;

import java.util.*;

/**
 * Socio de la cooperativa.
 * Mantiene una lista de cuentas (por simplicidad, solo de ahorro en este prototipo).
 */
public class Socio {
    private final String nombre;
    private final String cedula;
    private final Map<String, Cuenta> cuentas = new LinkedHashMap<>(); // numeroCuenta -> Cuenta

    public Socio(String nombre, String cedula) {
        this.nombre = Objects.requireNonNull(nombre, "nombre");
        this.cedula = Objects.requireNonNull(cedula, "cedula");
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas.values());
    }

    public Cuenta getCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    /** Abre una cuenta de ahorros validando que el número no esté repetido para el socio. */
    public CuentaAhorros abrirCuentaAhorros(String numeroCuenta, double interesAnual) throws CuentaDuplicadaException {
        if (cuentas.containsKey(numeroCuenta)) {
            throw new CuentaDuplicadaException("El socio " + nombre + " ya tiene la cuenta " + numeroCuenta);
        }
        CuentaAhorros nueva = new CuentaAhorros(numeroCuenta, interesAnual);
        cuentas.put(numeroCuenta, nueva);
        return nueva;
    }
}
