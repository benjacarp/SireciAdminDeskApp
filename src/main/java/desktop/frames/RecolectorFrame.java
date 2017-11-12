package desktop.frames;

import desktop.model.RecolectorTableModel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RecolectorFrame extends Stage {
    private final String empresa;
    private RecolectorTableModel table;

    public RecolectorFrame(String empresa) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.empresa = empresa;
        initComponents();
        cargarInfo();
        createStage();
    }

    private void createStage() {
        StackPane pane = new StackPane();
        pane.getChildren().addAll(table);
        Scene scene = new Scene(pane,550,300);
        this.setScene(scene);
    }

    private void cargarInfo() {

    }

    private void initComponents() {
        table = new RecolectorTableModel(300,300,empresa);
    }
}
