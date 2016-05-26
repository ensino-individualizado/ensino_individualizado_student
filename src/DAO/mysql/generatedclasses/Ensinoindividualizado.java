/**
 * This class is generated by jOOQ
 */
package DAO.mysql.generatedclasses;


import DAO.mysql.generatedclasses.tables.*;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
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
public class Ensinoindividualizado extends SchemaImpl {

	private static final long serialVersionUID = 1326072417;

	/**
	 * The reference instance of <code>ensinoindividualizado</code>
	 */
	public static final Ensinoindividualizado ENSINOINDIVIDUALIZADO = new Ensinoindividualizado();

	/**
	 * No further instances allowed
	 */
	private Ensinoindividualizado() {
		super("ensinoindividualizado");
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Alternativa.ALTERNATIVA,
			Aluno.ALUNO,
			Audio.AUDIO,
			AudioPlaylist.AUDIO_PLAYLIST,
			Avaliacao.AVALIACAO,
			AvaliacaoBloco.AVALIACAO_BLOCO,
			Bloco.BLOCO,
			Categoria.CATEGORIA,
			Enunciado.ENUNCIADO,
			Imagem.IMAGEM,
			Palavra.PALAVRA,
			PalavraSilaba.PALAVRA_SILABA,
			Playlist.PLAYLIST,
			Realiza.REALIZA,
			Resposta.RESPOSTA,
			Silaba.SILABA,
			Tentativa.TENTATIVA,
			Tipobloco.TIPOBLOCO,
			Turma.TURMA,
			Video.VIDEO,
			VideoPlaylist.VIDEO_PLAYLIST);
	}
}