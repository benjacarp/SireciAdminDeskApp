package desktop.model;

import desktop.apiadapter.ApiAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RecolectorTableModel extends TableView<Recolector> {
    private final String empresa;

    private TableColumn<Recolector, Integer> idColumn;
    private TableColumn<Recolector, String> nombreColumn;
    private TableColumn<Recolector, Integer> dniColumn;

    public RecolectorTableModel(int minWidth, int minHeight, String empresa) {
        this.empresa = empresa;
        mapColumns();
        update();
        this.getColumns().addAll(idColumn, nombreColumn, dniColumn);
        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    public void update() {
        ObservableList<Recolector> contenedoresParaMostrar = FXCollections.observableArrayList();

        List<Recolector> contenedores = null;

        contenedores = ApiAdapter.getRecolectores(empresa);

        for (Recolector c : contenedores) {
            contenedoresParaMostrar.add(c);
        }

        setItems(contenedoresParaMostrar);
        getSelectionModel().clearSelection();
    }

    private void mapColumns() {
        idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(60);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setPrefWidth(150);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        dniColumn = new TableColumn<>("DNI");
        dniColumn.setPrefWidth(60);
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
    }
}
