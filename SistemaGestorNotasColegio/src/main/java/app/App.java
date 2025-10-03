package app;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * SISTEMA COLEGIO V3 - Consola
 * -------------------------------------------------------------
 * Cumple con la "Evidencia de aprendizaje Unidad 3" (POO):
 * 1) Clases, subclases y relaciones (asociación/composición).
 * 2) Patrones de diseño: Singleton (Repositorio) + Strategy (cálculo de promedios).
 * 3) Programación funcional: uso de Streams/Lambdas (filter/map/forEach/collect, etc.).
 * 4) Gestión de errores y validaciones: try-catch, mensajes claros, programa robusto.
 * 5) Código comentado: líneas explicativas en puntos clave.
 *
 * Para ejecutar en consola (Java 11+):
 *   javac -d out $(find src -name "*.java") && java -cp out app.App
 */
public class App {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        seedDemoData(); // Carga datos de ejemplo para probar más rápido

        int opc;
        do {
            mostrarMenu();
            opc = leerEnteroSeguro("Opción: ");
            try {
                switch (opc) {
                    case 1 -> menuEstudiantes();
                    case 2 -> menuMaterias();
                    case 3 -> menuNotas();
                    case 4 -> menuConsultas();
                    case 0 -> System.out.println("Saliendo… ¡Hasta luego!");
                    default -> System.out.println("⚠️ Opción no válida. Intenta de nuevo.");
                }
            } catch (RuntimeException ex) {
                // Control centralizado de errores no verificados: el programa no se detiene
                System.out.println("❌ Ocurrió un error: " + ex.getMessage());
            }
            System.out.println();
        } while (opc != 0);
    }

    // ======================== MENÚS ========================
    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA COLEGIO V3 =====");
        System.out.println("1) Gestión de estudiantes");
        System.out.println("2) Gestión de materias");
        System.out.println("3) Registro y cálculo de notas");
        System.out.println("4) Consultas/Reportes (Streams)");
        System.out.println("0) Salir");
    }

    private static void menuEstudiantes() {
        int opc;
        do {
            System.out.println("\n--- Estudiantes ---");
            System.out.println("1) Crear estudiante");
            System.out.println("2) Listar estudiantes");
            System.out.println("3) Buscar por nombre");
            System.out.println("0) Volver");
            opc = leerEnteroSeguro("Opción: ");
            switch (opc) {
                case 1 -> crearEstudiante();
                case 2 -> listarEstudiantes();
                case 3 -> buscarEstudiantesPorNombre();
                case 0 -> {}
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opc != 0);
    }

    private static void menuMaterias() {
        int opc;
        do {
            System.out.println("\n--- Materias ---");
            System.out.println("1) Crear materia");
            System.out.println("2) Listar materias");
            System.out.println("0) Volver");
            opc = leerEnteroSeguro("Opción: ");
            switch (opc) {
                case 1 -> crearMateria();
                case 2 -> listarMaterias();
                case 0 -> {}
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opc != 0);
    }

    private static void menuNotas() {
        int opc;
        do {
            System.out.println("\n--- Notas ---");
            System.out.println("1) Registrar nota");
            System.out.println("2) Calcular promedio por estudiante y materia");
            System.out.println("3) Cambiar estrategia de promedio (Simple / Ponderado)");
            System.out.println("0) Volver");
            opc = leerEnteroSeguro("Opción: ");
            switch (opc) {
                case 1 -> registrarNota();
                case 2 -> calcularPromedio();
                case 3 -> cambiarEstrategiaPromedio();
                case 0 -> {}
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opc != 0);
    }

    private static void menuConsultas() {
        int opc;
        do {
            System.out.println("\n--- Consultas/Reportes (Streams) ---");
            System.out.println("1) Top N promedios por materia");
            System.out.println("2) Estudiantes con promedio general >= X");
            System.out.println("3) Materias cursadas por estudiante");
            System.out.println("0) Volver");
            opc = leerEnteroSeguro("Opción: ");
            switch (opc) {
                case 1 -> reporteTopPromediosPorMateria();
                case 2 -> reporteEstudiantesConPromedioMinimo();
                case 3 -> reporteMateriasPorEstudiante();
                case 0 -> {}
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opc != 0);
    }

    // ======================== ESTUDIANTES ========================
    private static void crearEstudiante() {
        System.out.println("\nNuevo estudiante");
        System.out.print("Nombre: ");
        String nombre = SC.nextLine().trim();
        if (nombre.isBlank()) {
            System.out.println("⚠️ El nombre no puede estar vacío.");
            return;
        }
        Estudiante est = new Estudiante(nombre);
        Repositorio.getInstance().guardarEstudiante(est);
        System.out.println("✅ Estudiante creado con id " + est.getId());
    }

    private static void listarEstudiantes() {
        System.out.println("\nListado de estudiantes:");
        var repo = Repositorio.getInstance();
        if (repo.getEstudiantes().isEmpty()) {
            System.out.println("(sin registros)");
            return;
        }
        repo.getEstudiantes().forEach(System.out::println);
    }

    private static void buscarEstudiantesPorNombre() {
        System.out.print("Nombre (parte): ");
        String q = SC.nextLine().trim().toLowerCase();
        var resultados = Repositorio.getInstance().getEstudiantes().stream()
                .filter(e -> e.getNombre().toLowerCase().contains(q)) // Programación funcional: filter
                .collect(Collectors.toList());
        if (resultados.isEmpty()) System.out.println("No hay coincidencias.");
        else resultados.forEach(System.out::println);
    }

    // ======================== MATERIAS ========================
    private static void crearMateria() {
        System.out.println("\nNueva materia");
        System.out.print("Nombre: ");
        String nombre = SC.nextLine().trim();
        if (nombre.isBlank()) {
            System.out.println("⚠️ El nombre no puede estar vacío.");
            return;
        }
        Materia m = MateriaFactory.crearMateriaBasica(nombre); // Patrón Factory Method
        Repositorio.getInstance().guardarMateria(m);
        System.out.println("✅ Materia creada con id " + m.getId());
    }

    private static void listarMaterias() {
        System.out.println("\nListado de materias:");
        var repo = Repositorio.getInstance();
        if (repo.getMaterias().isEmpty()) {
            System.out.println("(sin registros)");
            return;
        }
        repo.getMaterias().forEach(System.out::println);
    }

    // ======================== NOTAS / PROMEDIOS ========================
    private static void registrarNota() {
        var repo = Repositorio.getInstance();
        if (repo.getEstudiantes().isEmpty() || repo.getMaterias().isEmpty()) {
            System.out.println("⚠️ Debe haber al menos un estudiante y una materia.");
            return;
        }
        int idEst = leerEnteroSeguro("Id estudiante: ");
        Estudiante e = repo.buscarEstudiante(idEst).orElse(null);
        if (e == null) { System.out.println("❌ Estudiante no encontrado."); return; }

        int idMat = leerEnteroSeguro("Id materia: ");
        Materia m = repo.buscarMateria(idMat).orElse(null);
        if (m == null) { System.out.println("❌ Materia no encontrada."); return; }

        double valor = leerDoubleSeguro("Nota (0.0 a 5.0): ", 0.0, 5.0);
        double peso = leerDoubleSeguro("Peso (0-100, usual 25/30/etc.): ", 0, 100);

        // Composición: la materia posee/compone objetos Nota para un estudiante
        e.registrarNota(m, new Nota(valor, peso));
        System.out.println("✅ Nota registrada.");
    }

    private static void calcularPromedio() {
        var repo = Repositorio.getInstance();
        int idEst = leerEnteroSeguro("Id estudiante: ");
        Estudiante e = repo.buscarEstudiante(idEst).orElse(null);
        if (e == null) { System.out.println("❌ Estudiante no encontrado."); return; }

        int idMat = leerEnteroSeguro("Id materia: ");
        Materia m = repo.buscarMateria(idMat).orElse(null);
        if (m == null) { System.out.println("❌ Materia no encontrada."); return; }

        double prom = e.calcularPromedio(m); // Strategy aplicada aquí
        if (Double.isNaN(prom)) System.out.println("⚠️ No hay notas registradas para esa materia.");
        else System.out.printf("Promedio %s de %s: %.2f%n", m.getNombre(), e.getNombre(), prom);
    }

    private static void cambiarEstrategiaPromedio() {
        var repo = Repositorio.getInstance();
        System.out.println("Estrategías disponibles: 1) Simple  2) Ponderado");
        int opc = leerEnteroSeguro("Elige: ");
        switch (opc) {
            case 1 -> repo.setEstrategiaPromedio(new PromedioSimple());
            case 2 -> repo.setEstrategiaPromedio(new PromedioPonderado());
            default -> {
                System.out.println("⚠️ Opción inválida");
                return;
            }
        }
        System.out.println("✅ Estrategia actual: " + repo.getEstrategiaPromedio().nombre());
    }

    // ======================== REPORTES (STREAMS) ========================
    private static void reporteTopPromediosPorMateria() {
        var repo = Repositorio.getInstance();
        if (repo.getMaterias().isEmpty() || repo.getEstudiantes().isEmpty()) {
            System.out.println("⚠️ Faltan estudiantes o materias.");
            return;
        }
        int idMat = leerEnteroSeguro("Id materia: ");
        Materia m = repo.buscarMateria(idMat).orElse(null);
        if (m == null) { System.out.println("❌ Materia no encontrada."); return; }
        int topN = leerEnteroSeguro("¿Cuántos mostrar (Top N)?: ");

        // Stream: mapeamos cada estudiante a su promedio en la materia, filtramos NaN y ordenamos desc
        var ranking = repo.getEstudiantes().stream()
                .map(est -> new AbstractMap.SimpleEntry<>(est, est.calcularPromedio(m)))
                .filter(e -> !Double.isNaN(e.getValue()))
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(topN)
                .collect(Collectors.toList());

        if (ranking.isEmpty()) {
            System.out.println("No hay promedios para esa materia.");
            return;
        }

        System.out.println("\nTop " + topN + " en " + m.getNombre() + ":");
        AtomicInteger pos = new AtomicInteger(1);
        ranking.forEach(par -> System.out.printf("%d) %s -> %.2f%n",
                pos.getAndIncrement(), par.getKey().getNombre(), par.getValue()));
    }

    private static void reporteEstudiantesConPromedioMinimo() {
        var repo = Repositorio.getInstance();
        double min = leerDoubleSeguro("Promedio general mínimo (0-5): ", 0, 5);
        // Para el promedio general, combinamos todas las materias con Strategy vigente
        var ests = repo.getEstudiantes().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e, e.promedioGeneral()))
                .filter(par -> !Double.isNaN(par.getValue()) && par.getValue() >= min)
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());

        if (ests.isEmpty()) { System.out.println("(ninguno alcanza el mínimo)"); return; }
        System.out.println("\nEstudiantes con promedio >= " + min + ":");
        ests.forEach(par -> System.out.printf("%s -> %.2f%n", par.getKey().getNombre(), par.getValue()));
    }

    private static void reporteMateriasPorEstudiante() {
        var repo = Repositorio.getInstance();
        int idEst = leerEnteroSeguro("Id estudiante: ");
        Estudiante e = repo.buscarEstudiante(idEst).orElse(null);
        if (e == null) { System.out.println("❌ Estudiante no encontrado."); return; }

        var mats = e.getNotasPorMateria().keySet().stream()
                .sorted(Comparator.comparing(Materia::getNombre))
                .collect(Collectors.toList());

        if (mats.isEmpty()) { System.out.println("(sin materias registradas)"); return; }
        System.out.println("Materias de " + e.getNombre() + ":");
        mats.forEach(mat -> System.out.println("- " + mat.getNombre()));
    }

    // ======================== UTILIDADES I/O SEGURAS ========================
    private static int leerEnteroSeguro(String prompt) {
        return leerSeguro(prompt, () -> Integer.parseInt(SC.nextLine().trim()));
    }

    private static double leerDoubleSeguro(String prompt, double min, double max) {
        return leerSeguro(prompt, () -> {
            double v = Double.parseDouble(SC.nextLine().trim().replace(",", "."));
            if (v < min || v > max) throw new IllegalArgumentException("Valor fuera de rango [" + min + ", " + max + "]");
            return v;
        });
    }

    private static <T> T leerSeguro(String prompt, Supplier<T> reader) {
        // Método genérico con try-catch-finally para validar y no abortar el programa
        while (true) {
            System.out.print(prompt);
            try {
                T value = reader.get();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Formato inválido. Intente de nuevo.");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
            } finally {
                // finally: podría usarse para limpiar recursos; aquí dejamos visible su uso
            }
        }
    }

    private static void seedDemoData() {
        var repo = Repositorio.getInstance();
        // Profesores (relación de asociación con Materia)
        Profesor p1 = new Profesor("Ana Pérez");
        Profesor p2 = new Profesor("Carlos Díaz");
        repo.guardarProfesor(p1);
        repo.guardarProfesor(p2);

        // Materias vía Factory Method
        Materia mat1 = MateriaFactory.crearMateriaConProfesor("Matemáticas", p1);
        Materia mat2 = MateriaFactory.crearMateriaConProfesor("Lengua", p2);
        Materia mat3 = MateriaFactory.crearMateriaBasica("Ciencias");
        repo.guardarMateria(mat1);
        repo.guardarMateria(mat2);
        repo.guardarMateria(mat3);

        // Estudiantes
        Estudiante e1 = new Estudiante("Lucía");
        Estudiante e2 = new Estudiante("Marcos");
        Estudiante e3 = new Estudiante("Sofía");
        repo.guardarEstudiante(e1);
        repo.guardarEstudiante(e2);
        repo.guardarEstudiante(e3);

        // Notas (composición) + Strategy (ponderado por defecto)
        repo.setEstrategiaPromedio(new PromedioPonderado());
        e1.registrarNota(mat1, new Nota(4.5, 30));
        e1.registrarNota(mat1, new Nota(3.8, 30));
        e1.registrarNota(mat1, new Nota(4.0, 40));
        e1.registrarNota(mat2, new Nota(4.2, 50));
        e1.registrarNota(mat2, new Nota(3.9, 50));

        e2.registrarNota(mat1, new Nota(3.2, 50));
        e2.registrarNota(mat1, new Nota(4.1, 50));
        e2.registrarNota(mat3, new Nota(4.8, 100));

        e3.registrarNota(mat2, new Nota(4.9, 60));
        e3.registrarNota(mat2, new Nota(4.5, 40));
    }
}
