package colegio;

import java.util.List;

/**
 * Estrategia concreta: pondera las notas (ejemplo simple).
 */
public class EstrategiaPonderado implements EstrategiaNota {
    @Override
    public double calcular(List<Double> notas) {
        if (notas.isEmpty()) return 0.0;
        double suma = 0;
        for (int i = 0; i < notas.size(); i++) {
            suma += notas.get(i) * (i+1); // ponderación creciente
        }
        return suma / ((notas.size() * (notas.size()+1))/2);
    }
}
