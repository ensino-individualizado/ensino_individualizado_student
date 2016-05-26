package Visao.Dialog.SimpleMediaPlayerDialog;

import Visao.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class SimpleMediaPlayerController extends RegionController {

    @FXML
    private ImageView stop;

    @FXML
    private ImageView playPause;

    @FXML
    private MediaView view;

    private Media media;
    private MediaPlayer player;

    Image pauseImageCache = new Image(String.valueOf(getClass().getResource("/resources/icons/ic_pause.png")));
    Image playImageCache = new Image(String.valueOf(getClass().getResource("/resources/icons/ic_play.png")));

    private boolean isPlaying = false;

    @FXML
    private void playPause(){
        if(isPlaying){
            this.pause();
        }
        else{
            this.play();
        }
        isPlaying = !isPlaying;
    }

    @FXML
    public void stop(){
        this.player.stop();
        this.playPause.setImage(this.playImageCache);
        this.isPlaying = !this.isPlaying;
        this.stop.setDisable(true);
    }

    public void setMedia(String src) {

        String path = "file:///" + src.replace("\\", "/");
        path = path.replace(" ", "%20");

        this.media = new Media(path);
        this.player = new MediaPlayer(media);
        this.view.setMediaPlayer(player);

        this.player.setOnEndOfMedia(() -> playPause());
    }

    public void play(){
        player.play();
        stop.setDisable(false);
        playPause.setImage(this.pauseImageCache);
    }

    public void pause(){
        player.pause();
        playPause.setImage(this.playImageCache);
    }

    public MediaPlayer getPlayer(){
        return (this.player);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
