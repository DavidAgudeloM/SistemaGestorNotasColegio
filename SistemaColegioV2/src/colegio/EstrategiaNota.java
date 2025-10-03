package colegio;

import java.util.List;

/**
 * Interfaz Strategy: diferentes formas de calcular nota final.
 */
public interface EstrategiaNota {
    double calcular(List<Double> notas);
}
