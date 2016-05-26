package Visao;

import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.ApplicationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;

/**
 * Created by Gustavo Freitas on 12/10/2015.
 */
public class RegionLoader {

    private static RegionLoader regionLoader = new RegionLoader();

    /**
     * Carrega um region presente em um fxml e retorna o controlador com todas as refer?ncias b?sicas estabelecidas.
     * @param fatherController  Refer?ncia para o controlador pai. A refer?ncia para o applicationController associado deve existir.
     * @param fxmlLocation      Diret?rio do fxml.
     * @return                  Retorna o controlador do region carregado.
     * @throws IOException      ? lan?ada quando ocorre um erro de carregamento.
     */
    public RegionController load(RegionController fatherController, String fxmlLocation) throws IOException, NullPointerException {
        Region region = null;
        RegionController regionController;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlLocation));
        region = loader.load();

        regionController = loader.getController();
        regionController.setRegion(region);
        regionController.setFatherController(fatherController);
        fatherController.setChildrenController(regionController);

        return (regionController);
    }

    /**
     * Carrega um region presente em um fxml e retorna o controlador com todas as refer?ncias b?sicas estabelecidas.
     * Apenas utilize quando o Region a ser carregado ? o que ser? inserido dentro de um Scene, ou seja, n?o ? filho de
     * outro Region.
     * @param applicationController  Refer?ncia para o controlador da janela.
     * @param fxmlLocation      Diret?rio do fxml.
     * @return                  Retorna o controlador do region carregado.
     * @throws IOException      É lançada quando ocorre um erro de carregamento.
     */
    public RegionController load(ApplicationController applicationController, String fxmlLocation) throws IOException {
        Region region = null;
        RegionController regionController;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource(fxmlLocation));

        region = loader.load();
        regionController = loader.getController();
        regionController.setRegion(region);
        regionController.setInterfaceController(applicationController);

        return (regionController);
    }

    public static RegionLoader getInstance() {
        return (regionLoader);
    }
}
