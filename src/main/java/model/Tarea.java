package main.java.model;

public class Tarea {

    private int id;
    private String descripcion;
    private String completada;

    public Tarea(String descripcion, String completada) {
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public void Tarea(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCompletada(String completada) {
        this.completada = completada;
    }

    public String getCompletada() {
        return completada;
    }

}
