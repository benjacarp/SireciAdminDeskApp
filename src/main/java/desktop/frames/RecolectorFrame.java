package desktop.frames;

import com.sun.org.apache.regexp.internal.RE;
import desktop.apiadapter.ApiAdapter;
import desktop.model.Contenedor;
import desktop.model.Recolector;
import desktop.model.RecolectorTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RecolectorFrame extends Stage {
    private final String empresa;
    private RecolectorTableModel table;

    private Button btnNuevo;
    private Button btnModificar;
    private Button btnEliminar;
    private Recolector currentRecolector;

    private ListView<Contenedor> listView = new ListView<>();

    public RecolectorFrame(String empresa) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.empresa = empresa;
        initComponents();
        eventos();
        createStage();
    }

    private void createStage() {
        BorderPane borderPane = new BorderPane();
        HBox botonera = new HBox();
        botonera.getChildren().addAll(btnNuevo,btnModificar,btnEliminar);
        borderPane.setBottom(botonera);
        borderPane.setCenter(table);
        borderPane.setRight(listView);
        Scene scene = new Scene(borderPane,550,300);
        this.setScene(scene);
    }

    private void eventos() {
        btnNuevo.setOnAction(event -> nuevoContenedorClick());
        btnModificar.setOnAction(event -> modificarContenedorClick());
        btnEliminar.setOnAction(event -> eliminarContenedorClick());
    }

    private void eliminarContenedorClick() {
        ApiAdapter.deleteRecolector(empresa, currentRecolector.getId());
        table.update();
    }

    private void modificarContenedorClick() {
        Stage stage = new EditRecolectorFrame(empresa, table,currentRecolector);
        stage.setResizable(false);
        stage.show();
    }

    private void nuevoContenedorClick() {
        Stage stage = new EditRecolectorFrame(empresa, table, null);
        stage.setResizable(false);
        stage.show();
    }

    private void initComponents() {
        table = new RecolectorTableModel(300,300,empresa);
        table.getSelectionModel().selectedIndexProperty().addListener(e -> changeTableSelection());

        btnNuevo = new Button("Nuevo");
        btnModificar = new Button("Modificar");
        btnEliminar = new Button("Eliminar");
    }

    private void changeTableSelection() {
        this.currentRecolector = table.getSelectionModel().getSelectedItem();
        ObservableList<Contenedor> contenedores = FXCollections.observableArrayList();
        contenedores.addAll(currentRecolector.getContenedores());
        listView.setItems(contenedores);
    }
}
