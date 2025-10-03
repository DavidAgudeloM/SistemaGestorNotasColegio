package app;

import java.util.List;

public interface PromedioStrategy {
    double calcular(List<Nota> notas);
    default String nombre() { return getClass().getSimpleName(); }
}
