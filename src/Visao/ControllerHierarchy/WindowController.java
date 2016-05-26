package Visao.ControllerHierarchy;

import javafx.stage.Stage;

/**
 * Created by Gustavo Freitas on 20/03/2016.
 */
public class WindowController implements InterfaceWindowController {

    private Stage stage;
    private RegionController regionController = null;

    public WindowController(Stage stage, RegionController regionController){
        this.stage = stage;
        this.regionController = regionController;
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

    public Stage getStage() {
        return stage;
    }
}
