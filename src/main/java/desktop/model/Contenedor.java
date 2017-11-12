package desktop.model;

public class Contenedor {
    private int id;
    private String material;
    private int cordX;
    private int cordY;

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

    @Override
    public String toString() {
        return "Contenedor{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", cordX=" + cordX +
                ", cordY=" + cordY +
                '}';
    }
}
