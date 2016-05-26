/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Dialog;

import Visao.ControllerHierarchy.ApplicationController;
import Visao.ControllerHierarchy.RegionController;
import Visao.RegionLoader;
import javafx.scene.control.Dialog;

import java.io.IOException;

/**
 *
 * @author Gustavo Freitas
 */
public class SimpleDialog extends Dialog{

    private RegionController controller;

    public SimpleDialog(ApplicationController parent, String title, String header, String src) throws IOException {

        this.setTitle(title);
        this.setHeaderText(header);

        this.controller = RegionLoader.getInstance().load(parent, src);

        this.getDialogPane().setContent(controller.getRegion());
        this.getDialogPane().getContent().setStyle("-fx-background-color: #FFFFFF");

        this.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
            close();
        });
    }

    public RegionController getController() {
        return controller;
    }
}
