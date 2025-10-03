package com.cooprkc.transacciones;

import com.cooprkc.excepciones.SaldoInsuficienteException;
import com.cooprkc.modelo.Cuenta;

/** Retiro: resta saldo con control de insuficiencia. */
public class Retiro implements Transaccion {
    private final double monto;

    public Retiro(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El retiro debe ser positivo.");
        this.monto = monto;
    }

    @Override
    public void ejecutar(Cuenta cuenta) throws SaldoInsuficienteException {
        if (cuenta.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retirar $" + String.format("%,.2f", monto));
        }
        cuenta.retirar(monto);
    }

    @Override
    public double getMonto() {
        return monto;
    }
}
