package colegio;

import java.util.*;
import java.util.stream.Collectors;

public class SistemaColegio {
    private static SistemaColegio instancia;
    private List<Curso> cursos = new ArrayList<>();
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Profesor> profesores = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    private SistemaColegio() {}

    public static synchronized SistemaColegio getInstancia() {
        if (instancia == null) instancia = new SistemaColegio();
        return instancia;
    }

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n=== Menú Sistema Colegio ===");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Registrar Profesor");
            System.out.println("3. Crear Curso");
            System.out.println("4. Listar Estudiantes");
            System.out.println("5. Listar Profesores");
            System.out.println("6. Buscar Estudiante por Nombre (Stream)");
            System.out.println("7. Estudiantes con Nota > X (Stream)");
            System.out.println("8. Promedio de Curso (Stream)");
            System.out.println("9. Salir");
            System.out.print("Seleccione: ");
            opcion = Integer.parseInt(sc.nextLine());

            try {
                switch(opcion) {
                    case 1 -> registrarEstudiante();
                    case 2 -> registrarProfesor();
                    case 3 -> crearCurso();
                    case 4 -> listarEstudiantes();
                    case 5 -> listarProfesores();
                    case 6 -> buscarEstudiantePorNombre();
                    case 7 -> filtrarEstudiantesPorNota();
                    case 8 -> mostrarPromedioCurso();
                    case 9 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while(opcion != 9);
    }

    private void registrarEstudiante() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        Estudiante e = (Estudiante) FabricaPersona.crearEstudiante(id, nombre, apellido, new EstrategiaPromedio());
        estudiantes.add(e);
        System.out.println("Estudiante registrado: " + e.getDescripcion());
    }

    private void registrarProfesor() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        Profesor p = (Profesor) FabricaPersona.crearProfesor(id, nombre, apellido, especialidad);
        profesores.add(p);
        System.out.println("Profesor registrado: " + p.getDescripcion());
    }

    private void crearCurso() {
        System.out.print("Código del curso: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine();

        Curso c = new Curso(codigo, nombre);
        cursos.add(c);
        System.out.println("Curso creado: " + nombre);
    }

    private void listarEstudiantes() {
        estudiantes.forEach(e -> System.out.println(e.getDescripcion()));
    }

    private void listarProfesores() {
        profesores.stream().map(Profesor::getDescripcion).forEach(System.out::println);
    }

    private void buscarEstudiantePorNombre() {
        System.out.print("Ingrese nombre a buscar: ");
        String nombre = sc.nextLine();

        estudiantes.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .ifPresentOrElse(
                        e -> System.out.println("Encontrado: " + e.getDescripcion()),
                        () -> System.out.println("No se encontró estudiante con ese nombre.")
                );
    }

    private void filtrarEstudiantesPorNota() {
        System.out.print("Ingrese nota mínima: ");
        double umbral = Double.parseDouble(sc.nextLine());

        estudiantes.stream()
                .filter(e -> e.calcularNotaFinal() > umbral)
                .collect(Collectors.toList())
                .forEach(e -> System.out.println(e.getDescripcion()));
    }

    private void mostrarPromedioCurso() {
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos creados.");
            return;
        }

        cursos.forEach(c -> {
            double promedio = c.getEstudiantes().stream()
                    .mapToDouble(Estudiante::calcularNotaFinal)
                    .average()
                    .orElse(0.0);

            System.out.println("Curso: " + c.getNombre() + " -> Promedio: " + promedio);
        });
    }
}
