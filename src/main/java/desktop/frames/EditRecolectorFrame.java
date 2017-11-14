package desktop.frames;

import desktop.apiadapter.ApiAdapter;
import desktop.model.Contenedor;
import desktop.model.Recolector;
import desktop.model.RecolectorTableModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditRecolectorFrame extends Stage {

    private final String empresa;
    private final RecolectorTableModel table;
    private final Recolector currentRecolector;

    private TextField campoNombre = new TextField();
    private TextField campoDNI = new TextField();

    private Button createBtn;

    public EditRecolectorFrame(String empresa, RecolectorTableModel table, Recolector currentRecolector) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.currentRecolector = currentRecolector;
        this.table = table;
        this.empresa = empresa;
        initComponents();
        eventos();
        createStage();
    }

    private void createStage() {
        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(15,15,15,15));
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(createBtn);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(5);
        grid.setHgap(10);
        grid.setPrefWidth(300);
        Label nombreLabel = new Label("Nombre");
        GridPane.setConstraints(nombreLabel,0,0);
        GridPane.setConstraints(campoNombre,1,0);
        Label dniLabel = new Label("DNI");
        GridPane.setConstraints(dniLabel,0,1);
        GridPane.setConstraints(campoDNI,1,1);

        grid.getChildren().addAll(
                nombreLabel,campoNombre,dniLabel,campoDNI);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(createBtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid,hbox);

        Scene scene = new Scene(vBox,230,150);

        this.setScene(scene);
    }

    private void crear() {
        if (campoDNI.getText().matches("^(0|[1-9][0-9]*)$")) {
            Recolector recolector = new Recolector();
            recolector.setNombre(campoNombre.getText());
            recolector.setDni(Integer.parseInt(campoDNI.getText()));
            System.out.println(recolector);
            String response = ApiAdapter.createRecolector(recolector, empresa);
            System.out.println(response);
            table.update();
            this.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validacion");
            alert.setHeaderText("DNI invalido");
            alert.setContentText("El dni debe ser numerico");
            alert.show();
        }
    }

    private void eventos() {
        if (currentRecolector == null) {
            createBtn.setOnAction(event -> crear());
        } else {
            createBtn.setOnAction(event -> modificar());
        }
    }

    private void modificar() {
        if (campoDNI.getText().matches("^(0|[1-9][0-9]*)$")) {
            Recolector recolector = currentRecolector;
            recolector.setDni(Integer.parseInt(campoDNI.getText()));
            recolector.setNombre(campoNombre.getText());
            String response = ApiAdapter.modifyRecolector(recolector, empresa, recolector.getId());
            table.update();
            this.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validacion");
            alert.setHeaderText("DNI invalido");
            alert.setContentText("El dni debe ser numerico");
            alert.show();
        }
    }

    private void initComponents() {
        if (currentRecolector == null) {
            campoNombre = new TextField();
            campoDNI = new TextField();
            createBtn = new Button("Crear");
        } else {
            campoNombre = new TextField(currentRecolector.getNombre());
            campoDNI = new TextField("" + currentRecolector.getDni());
            createBtn = new Button("Modificar");
        }
    }
}
