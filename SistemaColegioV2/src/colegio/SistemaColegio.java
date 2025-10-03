package colegio;

import java.util.*;

/**
 * Singleton que administra el sistema y presenta menú en consola.
 */
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
            System.out.println("6. Salir");
            System.out.print("Seleccione: ");
            opcion = Integer.parseInt(sc.nextLine());

            try {
                switch(opcion) {
                    case 1 -> registrarEstudiante();
                    case 2 -> registrarProfesor();
                    case 3 -> crearCurso();
                    case 4 -> listarEstudiantes();
                    case 5 -> listarProfesores();
                    case 6 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while(opcion != 6);
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
        profesores.forEach(p -> System.out.println(p.getDescripcion()));
    }
}
