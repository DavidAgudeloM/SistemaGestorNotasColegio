package com.cooprkc.transacciones;

import com.cooprkc.excepciones.SaldoInsuficienteException;
import com.cooprkc.modelo.Cuenta;

/**
 * Interfaz Transaccion tal como se solicita.
 * - ejecutar(): aplica la operación sobre una cuenta.
 * - getMonto(): retorna el monto de la transacción.
 */
public interface Transaccion {
    void ejecutar(Cuenta cuenta) throws SaldoInsuficienteException;
    double getMonto();
}
