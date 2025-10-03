package app;

import java.util.List;

public class PromedioPonderado implements PromedioStrategy {
    @Override public double calcular(List<Nota> notas) {
        double sumaPesos = notas.stream().mapToDouble(Nota::getPeso).sum();
        if (sumaPesos <= 0) return Double.NaN;
        double total = notas.stream().mapToDouble(n -> n.getValor() * (n.getPeso() / 100.0)).sum();
        // Si los pesos no suman 100, se normaliza dividiendo por (sumaPesos/100)
        return total / (sumaPesos / 100.0);
    }
}
