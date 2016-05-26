package DAO;

import DAO.mysql.generatedclasses.tables.records.PlaylistRecord;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Midia.Midia;
import Modelo.RecursoDidatico.Midia.Video;
import Modelo.RecursoDidatico.PlayList.PlayList;
import org.jooq.Record1;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import static DAO.mysql.generatedclasses.tables.AudioPlaylist.AUDIO_PLAYLIST;
import static DAO.mysql.generatedclasses.tables.Playlist.PLAYLIST;
import static DAO.mysql.generatedclasses.tables.VideoPlaylist.VIDEO_PLAYLIST;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class PlayListDAO extends DAO<PlayList> {
    @Override
    public boolean novo(PlayList novo) throws SQLException, ClassNotFoundException {

        int counter = 0;

        PlaylistRecord playlist = GerenciadorBD.getContext().insertInto(PLAYLIST, PLAYLIST.TIPO)
                .values(novo.getTipo().getId())
                .returning().fetchOne();

        if(playlist != null){
            novo.setId(playlist.getIdplaylist());

            for(Midia midia : novo.getMidias()){
                if(midia instanceof Audio && novo.getTipo().equals(PlayList.TYPE.AUDIO)){
                    if(midia.getId() == -1) {
                        AudioDAO.getInstance().novo((Audio) midia);
                    }
                    GerenciadorBD.getContext().insertInto(AUDIO_PLAYLIST, AUDIO_PLAYLIST.IDAUDIO, AUDIO_PLAYLIST.IDPLAYLIST, AUDIO_PLAYLIST.NUMERO)
                            .values(midia.getId(), novo.getId(), counter).execute();

                }
                else if(midia instanceof Video && novo.getTipo().equals(PlayList.TYPE.VIDEO)){
                    if(midia.getId() == -1) {
                        VideoDAO.getInstance().novo((Video)midia);
                    }
                    GerenciadorBD.getContext().insertInto(VIDEO_PLAYLIST, VIDEO_PLAYLIST.IDVIDEO, VIDEO_PLAYLIST.IDPLAYLIST, VIDEO_PLAYLIST.NUMERO)
                            .values(midia.getId(), novo.getId(), counter);
                }
                else{
                    System.out.println("Playlist heterogênea não é suportada");
                }
                counter++;
            }

            return (true);
        }

        return (false);
    }

    @Override
    public PlayList obter(int id) throws SQLException, ClassNotFoundException {

        PlayList playList = null;

        PlaylistRecord record = GerenciadorBD.getContext().selectFrom(PLAYLIST).where(PLAYLIST.IDPLAYLIST.eq(id)).fetchOne();

        if(record != null){

            if(record.getTipo() == PlayList.TYPE.AUDIO.getId()){
                playList = new PlayList(PlayList.TYPE.AUDIO);
                playList.setMidias(this.obterAudiosByIdPlayList(id));
            }
            else{
                playList = new PlayList(PlayList.TYPE.VIDEO);
                playList.setMidias(this.obterVideosByIdPlayList(id));
            }

            playList.setId(record.getIdplaylist());
        }

        return (playList);
    }

    private LinkedList<Midia> obterAudiosByIdPlayList(int idPlatList){

        LinkedList<Midia> audios = null;

        Result<Record1<Integer>> result = GerenciadorBD.getContext().select(AUDIO_PLAYLIST.IDAUDIO)
                                                .from(AUDIO_PLAYLIST)
                                                .where(AUDIO_PLAYLIST.IDPLAYLIST.eq(idPlatList))
                                                .fetch();
        if(result != null) {
            audios = new LinkedList<>();
            for (Record1<Integer> a : result) {
                audios.addLast(AudioDAO.getInstance().obter(a.value1()));
            }
        }
        return (audios);
    }

    private LinkedList<Midia> obterVideosByIdPlayList(int idPlatList){

        LinkedList<Midia> videos = null;

        Result<Record1<Integer>> result = GerenciadorBD.getContext().select(VIDEO_PLAYLIST.IDVIDEO)
                .from(VIDEO_PLAYLIST)
                .where(VIDEO_PLAYLIST.IDPLAYLIST.eq(idPlatList))
                .fetch();

        if(result != null) {
            videos = new LinkedList<>();
            for (Record1<Integer> a : result) {
                videos.addLast(VideoDAO.getInstance().obter(a.value1()));
            }
        }
        return (videos);
    }

    @Override
    public Collection<PlayList> obterTodos() {
        return null;
    }

    @Override
    public boolean apagar(int id) {
        return false;
    }

    @Override
    public boolean apagarEmCascata(int id) {
        return false;
    }

    @Override
    public boolean atualizar(PlayList atualizado) {
        return false;
    }

    public static PlayListDAO getInstance(){
        return (new PlayListDAO());
    }
}
