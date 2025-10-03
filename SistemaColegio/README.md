# SistemaColegio

Proyecto de ejemplo en Java para la unidad: Programación Orientada a Objetos.

## Requisitos
- JDK 11+ (o cualquier versión moderna de Java)

## Compilar y ejecutar (línea de comandos)
1. Crear la estructura de carpetas `src/colegio`.
2. Colocar los archivos `.java` dentro de `src/colegio`.
3. Compilar:
   ```bash
   javac -d out src/colegio/*.java
   ```
4. Ejecutar:
   ```bash
   java -cp out colegio.Main
   ```

## Qué contiene
- Clases con herencia (Persona -> Estudiante/Profesor)
- Patron Singleton (SingletonSistema)
- Patron Factory Method (FabricaPersona)
- Programación funcional con Streams y lambdas
- Validaciones y manejo de errores (try-catch)
