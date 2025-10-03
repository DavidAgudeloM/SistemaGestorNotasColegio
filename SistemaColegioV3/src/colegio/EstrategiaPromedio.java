package colegio;

import java.util.List;

public class EstrategiaPromedio implements EstrategiaNota {
    @Override
    public double calcular(List<Double> notas) {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
