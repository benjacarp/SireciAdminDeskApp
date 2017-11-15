package desktop.model;

import desktop.apiadapter.ApiAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by ASUS on 12/11/2017.
 */
public class ContenedorTableModel extends TableView<Contenedor> {

    private final String empresa;
    private TableColumn<Contenedor, Integer> idColumn;
    private TableColumn<Contenedor, String> materialColumn;
    private TableColumn<Contenedor, Integer> xColumn;
    private TableColumn<Contenedor, Integer> yColumn;
    private TableColumn<Contenedor, Double> capacidadColumn;
    private TableColumn<Contenedor, String> recolectorColumn;

    public ContenedorTableModel(int minWidth, int minHeight, String empresa) {
        this.empresa = empresa;
        mapColumns();
        update();
        this.getColumns().addAll(idColumn, materialColumn, xColumn, yColumn,capacidadColumn,recolectorColumn);
        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    public void update() {
        ObservableList<Contenedor> contenedoresParaMostrar = FXCollections.observableArrayList();

        List<Contenedor> contenedores = null;

        contenedores = ApiAdapter.getContenedores(empresa);

        for (Contenedor c : contenedores) {
            contenedoresParaMostrar.add(c);
        }

        setItems(contenedoresParaMostrar);
        getSelectionModel().clearSelection();
    }

    private void mapColumns() {
        idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        materialColumn = new TableColumn<>("Material");
        materialColumn.setPrefWidth(100);
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));

        xColumn = new TableColumn<>("X");
        xColumn.setPrefWidth(80);
        xColumn.setCellValueFactory(new PropertyValueFactory<>("cordX"));

        yColumn = new TableColumn<>("Y");
        yColumn.setPrefWidth(80);
        yColumn.setCellValueFactory(new PropertyValueFactory<>("cordY"));

        capacidadColumn = new TableColumn<>("Capacidad");
        capacidadColumn.setPrefWidth(100);
        capacidadColumn.setCellValueFactory(new PropertyValueFactory<>("capacidad"));

        recolectorColumn = new TableColumn<>("Recolector");
        recolectorColumn.setPrefWidth(100);
        recolectorColumn.setCellValueFactory(new PropertyValueFactory<>("recolectorName"));
    }
}
