/**
 * This class is generated by jOOQ
 */
package DAO.mysql.generatedclasses.tables.records;


import DAO.mysql.generatedclasses.tables.Alternativa;
import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;


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
public class AlternativaRecord extends UpdatableRecordImpl<AlternativaRecord> implements Record7<Integer, Integer, Integer, Integer, Integer, Integer, Byte> {

	private static final long serialVersionUID = 48828429;

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.idalternativa</code>.
	 */
	public void setIdalternativa(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.idalternativa</code>.
	 */
	public Integer getIdalternativa() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.idtentativa</code>.
	 */
	public void setIdtentativa(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.idtentativa</code>.
	 */
	public Integer getIdtentativa() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.palavra_idpalavra</code>.
	 */
	public void setPalavraIdpalavra(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.palavra_idpalavra</code>.
	 */
	public Integer getPalavraIdpalavra() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.palavra_idcategoria</code>.
	 */
	public void setPalavraIdcategoria(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.palavra_idcategoria</code>.
	 */
	public Integer getPalavraIdcategoria() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.palavra_idaudio</code>.
	 */
	public void setPalavraIdaudio(Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.palavra_idaudio</code>.
	 */
	public Integer getPalavraIdaudio() {
		return (Integer) getValue(4);
	}

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.palavra_idimagem</code>.
	 */
	public void setPalavraIdimagem(Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.palavra_idimagem</code>.
	 */
	public Integer getPalavraIdimagem() {
		return (Integer) getValue(5);
	}

	/**
	 * Setter for <code>ensinoindividualizado.alternativa.resposta</code>. A resposta é considerada a correta caso este campo seja igual a true.
	 */
	public void setResposta(Byte value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.alternativa.resposta</code>. A resposta é considerada a correta caso este campo seja igual a true.
	 */
	public Byte getResposta() {
		return (Byte) getValue(6);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record5<Integer, Integer, Integer, Integer, Integer> key() {
		return (Record5) super.key();
	}

	// -------------------------------------------------------------------------
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<Integer, Integer, Integer, Integer, Integer, Integer, Byte> fieldsRow() {
		return (Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<Integer, Integer, Integer, Integer, Integer, Integer, Byte> valuesRow() {
		return (Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Alternativa.ALTERNATIVA.IDALTERNATIVA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Alternativa.ALTERNATIVA.IDTENTATIVA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return Alternativa.ALTERNATIVA.PALAVRA_IDPALAVRA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return Alternativa.ALTERNATIVA.PALAVRA_IDCATEGORIA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field5() {
		return Alternativa.ALTERNATIVA.PALAVRA_IDAUDIO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field6() {
		return Alternativa.ALTERNATIVA.PALAVRA_IDIMAGEM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Byte> field7() {
		return Alternativa.ALTERNATIVA.RESPOSTA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getIdalternativa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getIdtentativa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getPalavraIdpalavra();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getPalavraIdcategoria();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value5() {
		return getPalavraIdaudio();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value6() {
		return getPalavraIdimagem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Byte value7() {
		return getResposta();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value1(Integer value) {
		setIdalternativa(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value2(Integer value) {
		setIdtentativa(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value3(Integer value) {
		setPalavraIdpalavra(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value4(Integer value) {
		setPalavraIdcategoria(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value5(Integer value) {
		setPalavraIdaudio(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value6(Integer value) {
		setPalavraIdimagem(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord value7(Byte value) {
		setResposta(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlternativaRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, Integer value6, Byte value7) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AlternativaRecord
	 */
	public AlternativaRecord() {
		super(Alternativa.ALTERNATIVA);
	}

	/**
	 * Create a detached, initialised AlternativaRecord
	 */
	public AlternativaRecord(Integer idalternativa, Integer idtentativa, Integer palavraIdpalavra, Integer palavraIdcategoria, Integer palavraIdaudio, Integer palavraIdimagem, Byte resposta) {
		super(Alternativa.ALTERNATIVA);

		setValue(0, idalternativa);
		setValue(1, idtentativa);
		setValue(2, palavraIdpalavra);
		setValue(3, palavraIdcategoria);
		setValue(4, palavraIdaudio);
		setValue(5, palavraIdimagem);
		setValue(6, resposta);
	}
}
