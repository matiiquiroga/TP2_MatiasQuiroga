package main.java.controller;
import main.java.model.BaseDeDatosException;
import main.java.model.Tarea;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static main.java.controller.Processor.filtrarTareasCompletadas;
import static main.java.controller.Processor.guardarTareasEnArchivo;

public class Controller {

    public static void AppTareas() {

        Scanner scanner = new Scanner(System.in);
        int opcion=0;
        String continuar="S";
        List<Tarea> tareas = Processor.cargarArchivoTareas("/Users/A128751/Desktop/TP1_QuirogaMatias/src/main/java/archivoDB/Tareas.csv");
        //List<Tarea> tareas = Processor.cargarArchivoTareas("/432143Users/A128751/Desktop/TP1_QuirogaMatias/src/main/java/archivoDB/Tareas.csv");

        while(continuar.equalsIgnoreCase("S") ) {
            System.out.println("\nMenu:\n");
            System.out.println("1_Agregar Tarea");
            System.out.println("2_Listar Tareas");
            System.out.println("3_Eliminar Tarea");
            System.out.println("4_Marcar Tarea como completada");
            System.out.println("5_Listar Tareas completadas");
            System.out.println("6_Salir");
            System.out.println("\nIngrese una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Tarea tarea = Processor.crearTarea(scanner);
                    tareas.add(tarea);
                    tarea.setId(tareas.indexOf(tarea));

                    //Profe: en la primer ejecucion deje esta ruta para visualizar la excepcion personalizada!
                    guardarTareasEnArchivo(tareas,"/Users/A128751/Desktop/TP1_QuirogaMatias/src/main/java/archivoDB/Tareas.csv");
                    break;
                case 2:
                    Processor.listarTareas(tareas);
                    break;
                case 3:
                    Processor.listarTareas(tareas);
                    String msg= Processor.eliminarTarea(scanner, tareas);
                    System.out.println(msg);
                    Processor.listarTareas(tareas);
                    guardarTareasEnArchivo(tareas,"/Users/A128751/Desktop/TP1_QuirogaMatias/src/main/java/archivoDB/Tareas.csv");
                    break;
                case 4:
                    Processor.listarTareas(tareas);
                    Processor.marcarTareaCompletada(scanner, tareas);
                    Processor.listarTareas(tareas);
                    guardarTareasEnArchivo(tareas,"/Users/A128751/Desktop/TP1_QuirogaMatias/src/main/java/archivoDB/Tareas.csv");
                    break;
                case 5:
                    filtrarTareasCompletadas(tareas, t -> t.getCompletada().equalsIgnoreCase("si") );
                    break;
                case 6:
                    System.out.println(Processor.salirAplicacion());
                    exit(0);
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

            continuar = Processor.continuarAplicacion(scanner);

            if (continuar.equalsIgnoreCase("N") || continuar.equalsIgnoreCase("n")) {
                System.out.println(Processor.salirAplicacion());
                exit(0);
            }
        }

    }
}