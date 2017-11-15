package desktop.model;

public class Contenedor {
    private int id;
    private String material;
    private double cordX;
    private double cordY;
    private String recolectorName;
    private double capacidad;

    public Contenedor() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getCordX() {
        return cordX;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCordY() {
        return cordY;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }

    public String getRecolectorName() {
        return recolectorName;
    }

    public void setRecolectorName(String recolectorName) {
        this.recolectorName = recolectorName;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Contenedor{" +
                "id=" + id +
                ", material='" + material + '\'' +
                "\n cordX=" + cordX +
                ", cordY=" + cordY +
                "\n recolectorName='" + recolectorName + '\'' +
                "\n capacidad=" + capacidad +
                '}';
    }
}
