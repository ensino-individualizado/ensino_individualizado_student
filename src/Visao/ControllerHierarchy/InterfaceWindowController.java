package Visao.ControllerHierarchy;

import javafx.stage.Stage;

/**
 * Created by Gustavo Freitas on 20/03/2016.
 */
public interface InterfaceWindowController {
    public abstract void show();
    public abstract void hide();
    public abstract void showAndWait();
    public abstract RegionController getRegionController();
    public abstract Stage getStage();
}
