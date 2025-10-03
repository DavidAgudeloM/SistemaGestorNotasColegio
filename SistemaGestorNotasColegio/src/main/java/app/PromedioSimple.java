package app;

import java.util.List;

public class PromedioSimple implements PromedioStrategy {
    @Override public double calcular(List<Nota> notas) {
        return notas.stream().mapToDouble(Nota::getValor).average().orElse(Double.NaN);
    }
}
