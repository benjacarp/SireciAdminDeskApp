package desktop.frames;

import desktop.apiadapter.ApiAdapter;
import desktop.model.Contenedor;
import desktop.model.ContenedorTableModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditContenedorFrame extends Stage {

    private final String empresa;
    private final ContenedorTableModel table;
    private final Contenedor currentContenedor;

    private TextField campoX = new TextField();
    private TextField campoY = new TextField();
    private TextField campoCapacidad = new TextField();
    private ComboBox<String> comboMaterial = new ComboBox<>();

    private String regexForCordenadas = "^-?[0-9]\\d*(\\.\\d+)?$";
    private String regexForCapacidad = "^[0-9]\\d*(\\.\\d+)?$";

    private Button createBtn;

    public EditContenedorFrame(String empresa, ContenedorTableModel table, Contenedor currentContenedor) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.currentContenedor = currentContenedor;
        this.table = table;
        this.empresa = empresa;
        initComponents();
        eventos();
        createStage();
    }

    private void eventos() {
        if (currentContenedor == null) {
            createBtn.setOnAction(event -> crear());
        } else {
            createBtn.setOnAction(event -> modificar());
        }
    }

    private void modificar() {
        if (validateNumericFields()
                ) {
            Contenedor contenedor = currentContenedor;
            contenedor.setMaterial(comboMaterial.getSelectionModel().getSelectedItem());
            contenedor.setCordX(Double.parseDouble(campoX.getText()));
            contenedor.setCordY(Double.parseDouble(campoY.getText()));
            contenedor.setCapacidad(Double.parseDouble(campoCapacidad.getText()));
            System.out.println(contenedor);
            ApiAdapter.modifyContenedor(contenedor, empresa, contenedor.getId());
            table.update();
            this.close();
        } else {
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validacion");
        alert.setHeaderText("Ubicacion incorrecta");
        alert.setContentText("Los campos X, Y y capacidad deben ser numericos (la capacidad no puede ser negativa");
        alert.show();
    }

    private boolean validateNumericFields() {
        return campoX.getText().matches(regexForCordenadas)
                && campoY.getText().matches(regexForCordenadas)
                && campoCapacidad.getText().matches(regexForCapacidad);
    }

    private void crear() {
        if (validateNumericFields()
                ) {
            Contenedor contenedor = new Contenedor();
            contenedor.setMaterial(comboMaterial.getSelectionModel().getSelectedItem());
            contenedor.setCordX(Double.parseDouble(campoX.getText()));
            contenedor.setCordY(Double.parseDouble(campoY.getText()));
            contenedor.setCapacidad(Double.parseDouble(campoCapacidad.getText()));
            System.out.println(contenedor);
            ApiAdapter.createContenedor(contenedor, empresa);
            table.update();
            this.close();
        } else {
            showAlert();
        }

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
        Label material = new Label("Material");
        GridPane.setConstraints(material,0,0);
        GridPane.setConstraints(comboMaterial,1,0);
        Label x = new Label("X");
        GridPane.setConstraints(x,0,1);
        GridPane.setConstraints(campoX,1,1);
        Label y = new Label("Y");
        GridPane.setConstraints(y,0,2);
        GridPane.setConstraints(campoY,1,2);
        Label capLabel = new Label("Capacidad");
        GridPane.setConstraints(capLabel,0,3);
        GridPane.setConstraints(campoCapacidad,1,3);

        grid.getChildren().addAll(
                material, comboMaterial,x,campoX,y,campoY,capLabel,campoCapacidad);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(createBtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid,hbox);

        Scene scene = new Scene(vBox,230,250);

        this.setScene(scene);
    }

    private void initComponents() {
        comboMaterial.getItems().addAll("pet","vidrio","aluminio");
        if (currentContenedor == null) {
            createBtn = new Button("Crear");
        } else {
            campoX = new TextField("" + currentContenedor.getCordX());
            campoY = new TextField("" + currentContenedor.getCordY());
            comboMaterial.getSelectionModel().select(currentContenedor.getMaterial());
            campoCapacidad.setText(String.valueOf(currentContenedor.getCapacidad()));
            createBtn = new Button("Modificar");
        }
    }
}
