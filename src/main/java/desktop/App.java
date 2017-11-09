package desktop;

import desktop.frames.LoginFrame;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new LoginFrame(this);
        primaryStage.setOnCloseRequest(event -> close());
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    private void close() {
        try {
            System.out.println("Good Bye");
            this.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
