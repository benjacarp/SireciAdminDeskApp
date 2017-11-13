package desktop.frames;

import desktop.apiadapter.ApiAdapter;
import desktop.model.Contenedor;
import desktop.model.ContenedorTableModel;
import desktop.model.Recolector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class AsignarRecolectorFrame extends Stage {

    private final int contenedorId;
    private final String empresa;
    private final ContenedorTableModel table;

    private ListView<Recolector> listView = new ListView<>();
    private Recolector currentRecolector;

    private Button asignarBtn;

    public AsignarRecolectorFrame(String empresa, ContenedorTableModel table, int id) {
        this.contenedorId = id;

        this.table = table;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.empresa = empresa;
        initComponents();
        createStage();
    }

    private void createStage() {
        VBox pane = new VBox();
        pane.getChildren().addAll(listView,asignarBtn);
        Scene scene = new Scene(pane,550,300);
        this.setScene(scene);
    }

    private void initComponents() {
        List<Recolector> recolectoresFromApi = ApiAdapter.getRecolectores(empresa);

        ObservableList<Recolector> recolectores = FXCollections.observableArrayList();
        recolectores.addAll(recolectoresFromApi);
        listView.setItems(recolectores);

        listView.getSelectionModel().selectedIndexProperty().addListener(e -> changeListSelection());

        asignarBtn = new Button("Asignar");
        asignarBtn.setOnAction(event -> asignar());
    }

    private void changeListSelection() {
        this.currentRecolector = listView.getSelectionModel().getSelectedItem();
    }

    private void asignar() {
        System.out.println("contendor: " + contenedorId + ", recolector: " + currentRecolector.getId());

        ApiAdapter.asignarRecolector(contenedorId, currentRecolector.getId());
        this.table.update();
        this.close();
    }
}
