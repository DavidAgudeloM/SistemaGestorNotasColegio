package colegio;

public class Validaciones {
    public static void validarNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
    }

    public static void validarNota(double nota) throws IllegalArgumentException {
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 5");
        }
    }
}
