package Visao.ControllerHierarchy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Gustavo Freitas on 12/10/2015.
 */
public abstract class ApplicationController extends Application implements Controller, InterfaceWindowController {

    private Stage stage;
    private RegionController regionController = null;

    public void start(Stage primaryStage, String fxmlLocation, String title) throws Exception {
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlLocation));

        parent = loader.load();
        primaryStage.setScene(new Scene(parent));

        this.stage = primaryStage;
        this.regionController = loader.getController();
        System.out.println(regionController);
        this.regionController.setInterfaceController(this);
    }

    public void show(){
        this.stage.show();
    }

    public void hide(){
        this.stage.hide();
    }

    public void showAndWait(){
        this.stage.showAndWait();
    }

    public RegionController getRegionController() {
        return regionController;
    }

    public void setRegionController(RegionController regionController) {
        this.regionController = regionController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
