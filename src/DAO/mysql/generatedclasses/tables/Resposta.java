/**
 * This class is generated by jOOQ
 */
package DAO.mysql.generatedclasses.tables;


import DAO.mysql.generatedclasses.Ensinoindividualizado;
import DAO.mysql.generatedclasses.Keys;
import DAO.mysql.generatedclasses.tables.records.RespostaRecord;
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
public class Resposta extends TableImpl<RespostaRecord> {

	private static final long serialVersionUID = 2024595333;

	/**
	 * The reference instance of <code>ensinoindividualizado.resposta</code>
	 */
	public static final Resposta RESPOSTA = new Resposta();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RespostaRecord> getRecordType() {
		return RespostaRecord.class;
	}

	/**
	 * The column <code>ensinoindividualizado.resposta.idResposta</code>.
	 */
	public final TableField<RespostaRecord, Integer> IDRESPOSTA = createField("idResposta", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.resposta.realiza_idaluno</code>.
	 */
	public final TableField<RespostaRecord, Integer> REALIZA_IDALUNO = createField("realiza_idaluno", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.resposta.realiza_idavaliacao</code>.
	 */
	public final TableField<RespostaRecord, Integer> REALIZA_IDAVALIACAO = createField("realiza_idavaliacao", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.resposta.idtentativa</code>.
	 */
	public final TableField<RespostaRecord, Integer> IDTENTATIVA = createField("idtentativa", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.resposta.idalternativa</code>.
	 */
	public final TableField<RespostaRecord, Integer> IDALTERNATIVA = createField("idalternativa", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ensinoindividualizado.resposta.acerto</code>. Se true, o aluno acertou a tentativa.
	 */
	public final TableField<RespostaRecord, Byte> ACERTO = createField("acerto", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "Se true, o aluno acertou a tentativa.");

	/**
	 * Create a <code>ensinoindividualizado.resposta</code> table reference
	 */
	public Resposta() {
		this("resposta", null);
	}

	/**
	 * Create an aliased <code>ensinoindividualizado.resposta</code> table reference
	 */
	public Resposta(String alias) {
		this(alias, RESPOSTA);
	}

	private Resposta(String alias, Table<RespostaRecord> aliased) {
		this(alias, aliased, null);
	}

	private Resposta(String alias, Table<RespostaRecord> aliased, Field<?>[] parameters) {
		super(alias, Ensinoindividualizado.ENSINOINDIVIDUALIZADO, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<RespostaRecord, Integer> getIdentity() {
		return Keys.IDENTITY_RESPOSTA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RespostaRecord> getPrimaryKey() {
		return Keys.KEY_RESPOSTA_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RespostaRecord>> getKeys() {
		return Arrays.<UniqueKey<RespostaRecord>>asList(Keys.KEY_RESPOSTA_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<RespostaRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<RespostaRecord, ?>>asList(Keys.FK_RESPOSTA_REALIZA1, Keys.FK_RESPOSTA_TENTATIVA1, Keys.FK_RESPOSTA_ALTERNATIVA1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resposta as(String alias) {
		return new Resposta(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Resposta rename(String name) {
		return new Resposta(name, null);
	}
}
