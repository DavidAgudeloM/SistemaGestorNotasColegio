package com.cooprkc.modelo;

import java.util.Objects;

/**
 * Clase base Cuenta.
 * Contiene número de cuenta y saldo, con operaciones genéricas.
 * Pensada para heredar (p. ej. CuentaAhorros).
 */
public class Cuenta {
    private final String numeroCuenta;
    protected double saldo;

    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = Objects.requireNonNull(numeroCuenta, "numeroCuenta");
        this.saldo = 0.0;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    /** Depósito genérico (validación básica). */
    public void depositar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El depósito debe ser positivo.");
        saldo += monto;
    }

    /** Retiro genérico sin validar saldo (subclases/transacciones validan) */
    public void retirar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El retiro debe ser positivo.");
        saldo -= monto;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero='" + numeroCuenta +
                ", saldo=" + String.format("%,.2f", saldo) + '}';
    }
}
