# SistemaGestorNotasColegio

Sistema de gestión de notas para colegio (consola, Java, POO).

## Características
- Menú de gestión: estudiantes, materias, notas, reportes.
- Patrones: Singleton (Repositorio), Strategy (promedio simple/ponderado), Factory Method (Materias).
- Programación funcional con Streams (ranking, filtros, promedios).
- Validaciones y manejo de errores con `try-catch`.

## Requisitos
- Java 11 o superior.

## Ejecutar (Linux/Mac/WSL)
```bash
# Compilar a carpeta out
javac -d out $(find src -name "*.java")
# Ejecutar
java -cp out app.App
```

## Ejecutar (Windows PowerShell)
```powershell
# Compilar a carpeta out
Get-ChildItem -Recurse src\*.java | ForEach-Object {$_.FullName} | Set-Content sources.txt
javac -d out @sources.txt
# Ejecutar
java -cp out app.App
```

---
