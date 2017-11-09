package desktop.model;

public class Contenedor {
    private String numeroDeSerie;
    private String modelo;

    public Contenedor() {
    }

    public Contenedor(String numeroDeSerie, String modelo) {
        this.numeroDeSerie = numeroDeSerie;
        this.modelo = modelo;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Contenedor{" +
                "numeroDeSerie='" + numeroDeSerie + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
