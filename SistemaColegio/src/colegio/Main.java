package colegio;

import java.util.List;
import java.util.Scanner;

/**
 * Clase Main: ejemplo de uso del sistema.
 * Contiene manejo básico de errores y demostraciones de Streams.
 */
public class Main {
    public static void main(String[] args) {
        SingletonSistema sistema = SingletonSistema.getInstancia();

        // Crear profesores y estudiantes usando la fábrica
        try {
            Persona p1 = FabricaPersona.crearProfesor("P001", "María", "Gómez", "Matemáticas");
            Persona s1 = FabricaPersona.crearEstudiante("E001", "Juan", "Pérez");
            Persona s2 = FabricaPersona.crearEstudiante("E002", "Ana", "Ramírez");

            // Añadir al sistema (asegurando tipos con instanceof)
            if (p1 instanceof Profesor) sistema.agregarProfesor((Profesor)p1);
            if (s1 instanceof Estudiante) sistema.agregarEstudiante((Estudiante)s1);
            if (s2 instanceof Estudiante) sistema.agregarEstudiante((Estudiante)s2);

            // Crear curso y asignar profesor
            Curso curso = new Curso("C101", "Álgebra I");
            curso.asignarProfesor((Profesor)p1);

            // Agregar estudiantes al curso y asignar notas (con validaciones)
            Estudiante est1 = (Estudiante) s1;
            Estudiante est2 = (Estudiante) s2;

            try {
                Validaciones.validarNota(4.5);
                est1.agregarNota(4.5);

                Validaciones.validarNota(3.8);
                est2.agregarNota(3.8);

            } catch (IllegalArgumentException ex) {
                System.out.println("Error en notas: " + ex.getMessage());
            }

            curso.agregarEstudiante(est1);
            curso.agregarEstudiante(est2);

            sistema.agregarCurso(curso);

            // Mostrar datos: uso de Streams en el Main para listar profesores y promedios
            System.out.println("Profesores registrados:");
            sistema.getProfesores().stream()
                    .forEach(p -> System.out.println(p));

            System.out.println("Promedio del curso " + curso.getNombre() + ": " + curso.promedioCurso());

            List<Estudiante> buenos = curso.estudiantesConPromedioMayorQue(4.0);
            System.out.println("Estudiantes con promedio > 4.0: ");
            buenos.forEach(b -> System.out.println(b + " -> " + b.promedio()));

        } catch (Exception ex) {
            // Manejo general: evita que el programa se caiga sin control
            System.out.println("Ocurrió un error: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Interfaz mínima por consola para agregar un estudiante rápido (demostración)
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-- Demo: agregar estudiante desde consola --");
        try {
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();

            Validaciones.validarNombre(nombre);

            Estudiante nuevo = (Estudiante) FabricaPersona.crearEstudiante(id, nombre, apellido);
            sistema.agregarEstudiante(nuevo);
            System.out.println("Estudiante agregado: " + nuevo);
        } catch (IllegalArgumentException iae) {
            System.out.println("Entrada inválida: " + iae.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        sc.close();
    }
}
