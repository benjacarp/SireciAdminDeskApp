package desktop.model;

public class Contenedor {
    private int id;
    private String material;
    private int cordX;
    private int cordY;
    private String recolectorName;

    public Contenedor() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCordX() {
        return cordX;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public String getRecolectorName() {
        return recolectorName;
    }

    public void setRecolectorName(String recolectorName) {
        this.recolectorName = recolectorName;
    }

    @Override
    public String toString() {
        return "Contenedor: " + id + " ubicacion: " + cordX + "," + cordY + ". material: " + material;
    }
}
