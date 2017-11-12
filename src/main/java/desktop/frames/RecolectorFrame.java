package desktop.frames;

import desktop.model.Recolector;
import desktop.model.RecolectorTableModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public RecolectorFrame(String empresa) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.empresa = empresa;
        initComponents();
        eventos();
        createStage();
    }

    private void createStage() {
        VBox pane = new VBox();
        HBox botonera = new HBox();
        botonera.getChildren().addAll(btnNuevo,btnModificar,btnEliminar);
        pane.getChildren().addAll(table,botonera);
        Scene scene = new Scene(pane,550,300);
        this.setScene(scene);
    }

    private void eventos() {
        btnNuevo.setOnAction(event -> nuevoContenedorClick());
        btnModificar.setOnAction(event -> modificarContenedorClick());
        btnEliminar.setOnAction(event -> eliminarContenedorClick());
    }

    private void eliminarContenedorClick() {
        System.out.println(currentRecolector);
    }

    private void modificarContenedorClick() {
        System.out.println(currentRecolector);
    }

    private void nuevoContenedorClick() {
        System.out.println("nuevo recolector");
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
    }
}
