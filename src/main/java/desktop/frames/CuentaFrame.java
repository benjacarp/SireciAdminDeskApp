package desktop.frames;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import desktop.apiadapter.ApiAdapter;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CuentaFrame extends Stage {
    private final String empresa;

    private Label nombreLabel = new Label("");
    private Label direccionLabel = new Label("");
    private Label cuitLabel = new Label("");

    public CuentaFrame(String empresa) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.empresa = empresa;
        initComponents();
        createStage();
    }

    private void createStage() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(nombreLabel,cuitLabel,direccionLabel);
        Scene scene = new Scene(vBox,230,150);
        this.setScene(scene);
    }

    private void initComponents() {
        String nombre;
        String cuit;
        String direccion;

        String response = ApiAdapter.infoEmpresa(empresa);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray;
        try {
            jsonArray = mapper.readTree(response);
            nombre = String.valueOf(jsonArray.get("nombre"));
            cuit = String.valueOf(jsonArray.get("cuit"));
            direccion = String.valueOf(jsonArray.get("direccion"));

        } catch (IOException e) {
            nombre = "no se pudo cargar el nombre de la empresa";
            cuit = "no se pudo cargar el cuit de la empresa";
            direccion = "no se pudo cargar la direccion de la empresa";
        }

        nombreLabel.setText(nombre);
        cuitLabel.setText(cuit);
        direccionLabel.setText(direccion);

    }
}
