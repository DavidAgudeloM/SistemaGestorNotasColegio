package colegio;

/**
 * Punto de entrada: inicia el menú del sistema.
 */
public class Main {
    public static void main(String[] args) {
        SistemaColegio sistema = SistemaColegio.getInstancia();
        sistema.iniciarMenu();
    }
}
