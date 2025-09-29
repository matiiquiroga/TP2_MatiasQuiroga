package main.java.model;

import java.util.List;
import java.util.function.Predicate;

public class InterfazFuncional {

    public static void mostrarTareasFiltradas(List<Tarea> tareas, Predicate<Tarea> filtro) {
        tareas.stream()
                .filter(filtro)
                .forEach(t -> System.out.println("Descripción: " + t.getDescripcion() + " | Completada: " + t.getCompletada()));
    }

}
