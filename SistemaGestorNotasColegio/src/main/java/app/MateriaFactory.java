package app;

public class MateriaFactory {
    private MateriaFactory() {}

    public static Materia crearMateriaBasica(String nombre) {
        return new Materia(nombre);
    }

    public static Materia crearMateriaConProfesor(String nombre, Profesor profesor) {
        Materia m = new Materia(nombre);
        m.setProfesor(profesor);
        return m;
    }
}
