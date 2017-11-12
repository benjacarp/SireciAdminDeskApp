package desktop.frames;

import desktop.apiadapter.ApiAdapter;
import desktop.model.Contenedor;
import desktop.model.ContenedorTableModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class EditContenedorFrame extends Stage {

    private final String empresa;
    private final ContenedorTableModel table;

    private TextField campoX = new TextField();
    private TextField campoY = new TextField();
    private TextField campoMaterial = new TextField();

    private Button createBtn;

    public EditContenedorFrame(String empresa, ContenedorTableModel table) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.table = table;
        this.empresa = empresa;
        initComponents();
        eventos();
        createStage();
    }

    private void eventos() {
        createBtn.setOnAction(event -> crear());
    }

    private void crear() {
        Contenedor contenedor = new Contenedor();
        contenedor.setMaterial(campoMaterial.getText());
        contenedor.setCordX(Integer.parseInt(campoX.getText()));
        contenedor.setCordY(Integer.parseInt(campoY.getText()));
        System.out.println(contenedor);
        String response = ApiAdapter.createContenedor(contenedor, empresa);
        table.update();
        this.close();
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
        GridPane.setConstraints(campoMaterial,1,0);
        Label x = new Label("X");
        GridPane.setConstraints(x,0,1);
        GridPane.setConstraints(campoX,1,1);
        Label y = new Label("Y");
        GridPane.setConstraints(y,0,2);
        GridPane.setConstraints(campoY,1,2);

        grid.getChildren().addAll(
                material,campoMaterial,x,campoX,y,campoY);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(createBtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid,hbox);

        Scene scene = new Scene(vBox,230,150);

        this.setScene(scene);
    }

    private void initComponents() {
        createBtn = new Button("Crear");
    }
}