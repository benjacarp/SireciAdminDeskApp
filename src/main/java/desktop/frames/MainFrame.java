package desktop.frames;

import desktop.model.Recolector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Stage {

    private final String empresa;

    private MenuBar menuBar;
    private Menu menuRecolectores;
    private Menu menuContenedores;
    private Menu menuCuenta;
    private MenuItem menuItemVerRecolectores;
    private MenuItem menuItemAltaRecolectores;
    private MenuItem menuItemVerContenedores;
    private MenuItem menuItemPrestamos;
    private MenuItem menuItemMiCuenta;

    private MenuItem menuItemAltaContenedores;

    public MainFrame(String message) {
        this.empresa = message.substring(1,message.length()-1);
        initComponents();
        cargarInfo();
        createStage();
    }

    private void cargarInfo() {
    }

    private void initComponents() {
        menuBar = new MenuBar();

        menuRecolectores = new Menu("Recolectores");
        menuContenedores = new Menu("Contenedores");
        menuCuenta = new Menu("Cuenta");

        menuItemVerRecolectores = new MenuItem("Ver Recolectores");
        menuItemAltaRecolectores = new MenuItem("Nuevo Recolector");

        menuItemVerContenedores = new MenuItem("Ver Contenedores");
        menuItemAltaContenedores = new MenuItem("Nuevo Contenedor...");

        menuItemMiCuenta = new MenuItem("Mi Cuenta...");

        menuBar.getMenus().add(menuRecolectores);
        menuBar.getMenus().add(menuContenedores);
        menuBar.getMenus().add(menuCuenta);

        menuRecolectores.getItems().add(menuItemVerRecolectores);
        menuRecolectores.getItems().add(menuItemAltaRecolectores);
        menuContenedores.getItems().add(menuItemVerContenedores);
        menuContenedores.getItems().add(menuItemAltaContenedores);
        menuCuenta.getItems().add(menuItemMiCuenta);

        menuItemVerRecolectores.setOnAction(event -> verRecolectoresClick());
        menuItemAltaRecolectores.setOnAction(event -> altaRecolectoresClick());

        menuItemVerContenedores.setOnAction(event -> verContenedoresClick());
        menuItemAltaContenedores.setOnAction(event -> altaContenedoresClick());

        menuItemMiCuenta.setOnAction(event -> miCuentaClick());
    }

    private void miCuentaClick() {
        Stage stage = new CuentaFrame(empresa);
        stage.setResizable(false);
        stage.show();
    }

    private void altaRecolectoresClick() {
        System.out.println("alta recolectores");
    }

    private void verRecolectoresClick() {
        System.out.println("ver recolectores");
    }

    private void altaContenedoresClick() {
        System.out.println("alta contenedor");
    }

    private void verContenedoresClick() {
        System.out.println("ver contenedores");
        Stage stage = new ContenedorFrame(empresa);
        stage.setResizable(false);
        stage.show();
    }

    private ObservableList<String> cargarContenedores() {
        ObservableList<String> contenedores = FXCollections.observableArrayList();
        contenedores.add("1234");
        contenedores.add("5134");
        return contenedores;
    }

    private ObservableList<Recolector> cargarRecolectores() {
        ObservableList<Recolector> recolectores = FXCollections.observableArrayList();
        recolectores.add(new Recolector("Juan","Esquiu 1591"));
        recolectores.add(new Recolector("Pedro","Solano Vera 643"));
        return recolectores;
    }

    private void createStage() {
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);

//        VBox centerVBox = new VBox();
//        centerVBox.setPadding(new Insets(20,20,20,20));
//        HBox botonera = new HBox();
//        centerVBox.getChildren().addAll(new Label("Contenedores"),contenedoresList,botonera);
//        botonera.getChildren().addAll(new Button("Ver"));
//        layout.setCenter(centerVBox);
//
//        VBox rightVBox = new VBox();
//        rightVBox.setPadding(new Insets(20,20,20,20));
//        rightVBox.getChildren().addAll(new Label("Recolectores"),recolectoresList);
//        layout.setRight(rightVBox);

        Scene scene = new Scene(layout, 800, 600);
        setScene(scene);
    }
}
