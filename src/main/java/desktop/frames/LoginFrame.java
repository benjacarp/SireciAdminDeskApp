package desktop.frames;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import desktop.App;
import desktop.apiadapter.ApiAdapter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginFrame extends Stage {

    private final App app;
    private Label userLabel = new Label("User");
    private TextField userTextField = new TextField();

    private Label passwordLabel = new Label("Pass");
    private PasswordField passwordTextField = new PasswordField();

    private Button logInButton = new Button("Log In");
    private Label messageLabel = new Label("");

    public LoginFrame(App app) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.app = app;
        setEvents();
        createStage();
    }

    private void setEvents() {
        logInButton.setOnAction(event -> logInAction());
    }

    private void createStage() {
        this.setTitle("Log in...");

        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(15,15,15,15));
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(logInButton);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(5);
        grid.setHgap(10);
        grid.setPrefWidth(300);
        GridPane.setConstraints(userLabel,0,0);
        GridPane.setConstraints(userTextField,1,0);
        GridPane.setConstraints(passwordLabel,0,1);
        GridPane.setConstraints(passwordTextField,1,1);

        grid.getChildren().addAll(
                userLabel,userTextField,passwordLabel,passwordTextField,logInButton);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(logInButton,messageLabel);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid,hbox);

        Scene scene = new Scene(vBox,230,150);

        this.setScene(scene);
    }

    private void logInAction() {
        System.out.println("Log in...");
        String response = null;
        try {
            this.messageLabel.setTextFill(Color.RED);
            response = ApiAdapter.logIn(userTextField.getText(), passwordTextField.getText());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonArray;
            jsonArray = mapper.readTree(response);

            String status = String.valueOf(jsonArray.get("success"));
            String message = String.valueOf(jsonArray.get("message"));

//            if (status.equals("true")) {
            if (true) {
                initMainFrame();
            } else {
                this.messageLabel.setText("   " + message);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            this.messageLabel.setText("Server down");
        }
    }

    private void initMainFrame() {
        Stage stage = new MainFrame();
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                app.stop(); //cuando se cierra el main se cierra la aplicacion
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage.show();
        this.close();
    }

}
