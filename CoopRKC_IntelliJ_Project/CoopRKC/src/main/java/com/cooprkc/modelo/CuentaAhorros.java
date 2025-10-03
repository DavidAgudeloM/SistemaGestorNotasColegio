package com.cooprkc.modelo;

/**
 * Subclase de Cuenta que aplica interés.
 * interesAnual: tasa en [0,1] (ej: 0.02 = 2%).
 */
public class CuentaAhorros extends Cuenta {
    private double interesAnual;

    public CuentaAhorros(String numeroCuenta, double interesAnual) {
        super(numeroCuenta);
        if (interesAnual < 0) throw new IllegalArgumentException("El interés no puede ser negativo.");
        this.interesAnual = interesAnual;
    }

    public double getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(double interesAnual) {
        if (interesAnual < 0) throw new IllegalArgumentException("El interés no puede ser negativo.");
        this.interesAnual = interesAnual;
    }

    /** Aplica interés simple sobre el saldo actual. */
    public void aplicarInteres() {
        this.saldo += this.saldo * this.interesAnual;
    }

    @Override
    public String toString() {
        return "CuentaAhorros{" +
                "numero='" + getNumeroCuenta() +
                ", saldo=" + String.format("%,.2f", getSaldo()) +
                ", interesAnual=" + (interesAnual * 100) + "%%}";
    }
}
