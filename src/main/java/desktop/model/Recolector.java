package desktop.model;

import java.util.ArrayList;
import java.util.List;

public class Recolector {
    private int id;
    private int dni;
    private String nombre;
    private List<Contenedor> contenedores = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Contenedor> getContenedores() {
        return contenedores;
    }

    @Override
    public String toString() {
        return "Recolector{" +
                "id=" + id +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
