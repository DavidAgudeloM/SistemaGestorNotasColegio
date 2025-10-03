package com.cooprkc.transacciones;

import com.cooprkc.modelo.Cuenta;

/** Depósito: suma saldo. */
public class Deposito implements Transaccion {
    private final double monto;

    public Deposito(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El depósito debe ser positivo.");
        this.monto = monto;
    }

    @Override
    public void ejecutar(Cuenta cuenta) {
        cuenta.depositar(monto);
    }

    @Override
    public double getMonto() {
        return monto;
    }
}
