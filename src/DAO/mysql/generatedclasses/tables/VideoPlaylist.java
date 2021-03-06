/**
 * This class is generated by jOOQ
 */
package DAO.mysql.generatedclasses.tables;


import DAO.mysql.generatedclasses.Ensinoindividualizado;
import DAO.mysql.generatedclasses.Keys;
import DAO.mysql.generatedclasses.tables.records.VideoPlaylistRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VideoPlaylist extends TableImpl<VideoPlaylistRecord> {

	private static final long serialVersionUID = 1437076702;

	/**
	 * The reference instance of <code>ensinoindividualizado.video_playlist</code>
	 */
	public static final VideoPlaylist VIDEO_PLAYLIST = new VideoPlaylist();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<VideoPlaylistRecord> getRecordType() {
		return VideoPlaylistRecord.class;
	}

	/**
	 * The column <code>ensinoindividualizado.video_playlist.idvideo</code>.
	 */
	public final TableField<VideoPlaylistRecord, Integer> IDVIDEO = createField("idvideo", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.video_playlist.idplaylist</code>.
	 */
	public final TableField<VideoPlaylistRecord, Integer> IDPLAYLIST = createField("idplaylist", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.video_playlist.numero</code>.
	 */
	public final TableField<VideoPlaylistRecord, Integer> NUMERO = createField("numero", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>ensinoindividualizado.video_playlist</code> table reference
	 */
	public VideoPlaylist() {
		this("video_playlist", null);
	}

	/**
	 * Create an aliased <code>ensinoindividualizado.video_playlist</code> table reference
	 */
	public VideoPlaylist(String alias) {
		this(alias, VIDEO_PLAYLIST);
	}

	private VideoPlaylist(String alias, Table<VideoPlaylistRecord> aliased) {
		this(alias, aliased, null);
	}

	private VideoPlaylist(String alias, Table<VideoPlaylistRecord> aliased, Field<?>[] parameters) {
		super(alias, Ensinoindividualizado.ENSINOINDIVIDUALIZADO, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<VideoPlaylistRecord> getPrimaryKey() {
		return Keys.KEY_VIDEO_PLAYLIST_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<VideoPlaylistRecord>> getKeys() {
		return Arrays.<UniqueKey<VideoPlaylistRecord>>asList(Keys.KEY_VIDEO_PLAYLIST_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<VideoPlaylistRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<VideoPlaylistRecord, ?>>asList(Keys.FK_VIDEO_PLAYLIST_VIDEO1, Keys.FK_VIDEO_PLAYLIST_PLAYLIST1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VideoPlaylist as(String alias) {
		return new VideoPlaylist(alias, this);
	}

	/**
	 * Rename this table
	 */
	public VideoPlaylist rename(String name) {
		return new VideoPlaylist(name, null);
	}
}
