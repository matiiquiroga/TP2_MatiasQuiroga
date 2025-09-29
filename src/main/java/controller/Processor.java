package main.java.controller;
import main.java.model.BaseDeDatosException;
import main.java.model.Tarea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import static main.java.model.InterfazFuncional.mostrarTareasFiltradas;

public class Processor {

    public static Tarea crearTarea(Scanner scanner) {
        System.out.print("\nIngrese la descripción de la tarea: ");
        scanner.nextLine();
        String descripcion = scanner.nextLine();
        System.out.print("¿La tarea está completada? (si/no): ");
        String completada = scanner.nextLine();

        return new Tarea(descripcion, completada);
    }

    public static void listarTareas(List<Tarea> tareas) {
        if (tareas == null || tareas.isEmpty()) {
            System.out.println("\nNo hay tareas registradas.\n");
            return;
        }
        System.out.println("\nLista de Tareas:\n");
        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            System.out.println((i + 1) + ". Descripción: " + t.getDescripcion() + " | Completada: " + t.getCompletada());
        }
    }

    public static String eliminarTarea(Scanner scanner, List<Tarea> tareas) {
        System.out.print("\nIngrese el ID de la tarea a eliminar: \n");

        int id = scanner.nextInt();

        for (Tarea t : tareas) {
            if (t.getId() == (id-1)) {
                tareas.remove(t);
                return "\nTarea eliminada.\n";
            }
        }
        return "\nTarea no encontrada.\n";
    }

    public static String marcarTareaCompletada(Scanner scanner, List<Tarea> tareas) {
        System.out.print("\nIngrese el ID de la tarea a marcar como completada: \n");
        int id = scanner.nextInt();
        for (Tarea t : tareas) {
            if (t.getId() == (id-1)) {
                t.setCompletada("si");
                return "\nTarea marcada como completada.\n";
            }
        }
        return "\nTarea no encontrada.\n";
    }

    public static String salirAplicacion() {
        return "\nSaliendo de la aplicación. ¡Hasta luego!\n";
    }

    public static String continuarAplicacion(Scanner scanner) {
        System.out.print("\n¿Desea continuar? (S/N): ");
        return scanner.next();
    }

    public static List<Tarea> cargarArchivoTareas(String tareasArchivo) {
        List<Tarea> tareas = new ArrayList<>();
        java.io.File archivo = new java.io.File(tareasArchivo);

        if (!archivo.exists()) {
            try {
                if (archivo.createNewFile()) {
                    System.out.println("\nCreando base de datos... ");
                    System.out.println("\nBase de datos creada con exito.");
                }
            } catch (IOException e) {
                throw new RuntimeException(new BaseDeDatosException("Error al crear la base de datos: " + e.getMessage()));
            }
            return tareas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(tareasArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",", 3);
                if (partes.length == 3) {
                    Tarea t = new Tarea(partes[1], partes[2]);
                    t.setId(Integer.parseInt(partes[0]));
                    tareas.add(t);
                }
            }
            System.out.println("\nBase de datos cargada con exito.\n");
        } catch (IOException e) {
            System.out.println("Error al leer tareas: " + e.getMessage());
        }
        return tareas;
    }

    public static void guardarTareasEnArchivo(List<Tarea> tareas, String tareasArchivo) {
        try (FileWriter writer = new FileWriter(tareasArchivo)) {
            for (Tarea t : tareas) {
                writer.write(t.getId() + "," + t.getDescripcion() + "," + t.getCompletada() + "\n");
            }
            System.out.println("\nBase de datos actualizada\n");
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }

    public static List<Tarea> filtrarTareasCompletadas(List<Tarea> tareas, Predicate<Tarea> filtro) {
        List<Tarea> resultado = new ArrayList<>();
        mostrarTareasFiltradas(tareas, filtro);
        return resultado;
    }
}
