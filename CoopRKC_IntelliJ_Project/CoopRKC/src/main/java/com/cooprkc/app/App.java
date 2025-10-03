package com.cooprkc.app;

import com.cooprkc.excepciones.CuentaDuplicadaException;
import com.cooprkc.excepciones.SaldoInsuficienteException;
import com.cooprkc.modelo.*;
import com.cooprkc.transacciones.Deposito;
import com.cooprkc.transacciones.Retiro;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "CO"));

        // 1) Crear la cooperativa y registrar socios
        Cooperativa coop = new Cooperativa("CoopRKC");
        Socio ana = new Socio("Ana López", "11111111");
        Socio carlos = new Socio("Carlos Ríos", "22222222");
        Socio beatriz = new Socio("Beatriz Gómez", "33333333");

        coop.registrarSocio(ana);
        coop.registrarSocio(carlos);
        coop.registrarSocio(beatriz);

        // 2) Abrir cuentas de ahorro para los socios (validación de número repetido)
        try {
            ana.abrirCuentaAhorros("AHO-001", 0.015); // 1.5% interés
            ana.abrirCuentaAhorros("AHO-002", 0.02);
            carlos.abrirCuentaAhorros("AHO-010", 0.02);
            beatriz.abrirCuentaAhorros("AHO-020", 0.025);
            // Forzar duplicada para ver el manejo de error:
            // ana.abrirCuentaAhorros("AHO-001", 0.03);
        } catch (CuentaDuplicadaException e) {
            System.out.println("[VALIDACIÓN] " + e.getMessage());
        }

        // 3) Operaciones de depósito y retiro (con control de errores)
        Deposito dep1 = new Deposito(500_000);
        Deposito dep2 = new Deposito(300_000);
        Retiro ret1 = new Retiro(120_000);
        Retiro retGrande = new Retiro(1_000_000);

        // Ejecutar transacciones sobre una cuenta
        CuentaAhorros ctaAna = (CuentaAhorros) ana.getCuenta("AHO-001");
        dep1.ejecutar(ctaAna); // saldo = 500.000
        dep2.ejecutar(ctaAna); // saldo = 800.000

        try {
            ret1.ejecutar(ctaAna); // saldo = 680.000
        } catch (SaldoInsuficienteException e) {
            System.out.println("[ERROR RETIRO] " + e.getMessage());
        }

        try {
            retGrande.ejecutar(ctaAna); // debe fallar
        } catch (SaldoInsuficienteException e) {
            System.out.println("[ERROR RETIRO] " + e.getMessage());
        }

        // Depositar a otras cuentas
        Deposito depCarlos = new Deposito(1_200_000);
        depCarlos.ejecutar(carlos.getCuenta("AHO-010"));

        Deposito depBea = new Deposito(450_000);
        depBea.ejecutar(beatriz.getCuenta("AHO-020"));

        // 4) Aplicar interés a las cuentas de ahorro
        ana.getCuentas().forEach(c -> {
            if (c instanceof CuentaAhorros) {
                ((CuentaAhorros) c).aplicarInteres();
            }
        });

        // 5) Programación funcional (streams)
        System.out.println("\n=== Programación Funcional (Streams) ===");

        // 5.1 Listar los nombres de todos los socios (map + forEach)
        System.out.println("Socios registrados:");
        coop.getSocios()
                .stream()
                .map(Socio::getNombre)
                .forEach(nombre -> System.out.println(" - " + nombre));

        // 5.2 Filtrar y mostrar cuentas con saldo > $500.000
        System.out.println("\nCuentas con saldo > $500.000:");
        List<Cuenta> cuentasRicas = coop.getSocios().stream()
                .flatMap(s -> s.getCuentas().stream())
                .filter(c -> c.getSaldo() > 500_000)
                .sorted(Comparator.comparingDouble(Cuenta::getSaldo).reversed())
                .collect(Collectors.toList());
        cuentasRicas.forEach(c ->
                System.out.println(" " + c.getNumeroCuenta() + " -> $" + String.format("%,.2f", c.getSaldo()))
        );

        // 5.3 Total del dinero en la cooperativa (reduce)
        double total = coop.getSocios().stream()
                .flatMap(s -> s.getCuentas().stream())
                .map(Cuenta::getSaldo)
                .reduce(0.0, Double::sum);
        System.out.println("\nTotal dinero en la cooperativa: $" + String.format("%,.2f", total));

        // 6) Listado de socios con sus cuentas (impresión amigable)
        System.out.println("\n=== Resumen de Socios y Cuentas ===");
        coop.getSocios().forEach(s -> {
            System.out.println("* " + s.getNombre() + " (" + s.getCedula() + ")");
            s.getCuentas().forEach(c ->
                    System.out.println("   - " + c)
            );
        });

        System.out.println("\nDemostración finalizada.");
    }
}
