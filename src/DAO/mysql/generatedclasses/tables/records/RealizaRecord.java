/**
 * This class is generated by jOOQ
 */
package DAO.mysql.generatedclasses.tables.records;


import DAO.mysql.generatedclasses.tables.Realiza;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import java.sql.Date;


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
public class RealizaRecord extends UpdatableRecordImpl<RealizaRecord> implements Record4<Integer, Integer, Date, Byte> {

	private static final long serialVersionUID = 823744520;

	/**
	 * Setter for <code>ensinoindividualizado.realiza.idaluno</code>.
	 */
	public void setIdaluno(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.realiza.idaluno</code>.
	 */
	public Integer getIdaluno() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>ensinoindividualizado.realiza.idavaliacao</code>.
	 */
	public void setIdavaliacao(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.realiza.idavaliacao</code>.
	 */
	public Integer getIdavaliacao() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>ensinoindividualizado.realiza.dataRealizacao</code>.
	 */
	public void setDatarealizacao(Date value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.realiza.dataRealizacao</code>.
	 */
	public Date getDatarealizacao() {
		return (Date) getValue(2);
	}

	/**
	 * Setter for <code>ensinoindividualizado.realiza.estado</code>.
	 */
	public void setEstado(Byte value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.realiza.estado</code>.
	 */
	public Byte getEstado() {
		return (Byte) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record2<Integer, Integer> key() {
		return (Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, Integer, Date, Byte> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, Integer, Date, Byte> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Realiza.REALIZA.IDALUNO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Realiza.REALIZA.IDAVALIACAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field3() {
		return Realiza.REALIZA.DATAREALIZACAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Byte> field4() {
		return Realiza.REALIZA.ESTADO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getIdaluno();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getIdavaliacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value3() {
		return getDatarealizacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Byte value4() {
		return getEstado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RealizaRecord value1(Integer value) {
		setIdaluno(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RealizaRecord value2(Integer value) {
		setIdavaliacao(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RealizaRecord value3(Date value) {
		setDatarealizacao(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RealizaRecord value4(Byte value) {
		setEstado(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RealizaRecord values(Integer value1, Integer value2, Date value3, Byte value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached RealizaRecord
	 */
	public RealizaRecord() {
		super(Realiza.REALIZA);
	}

	/**
	 * Create a detached, initialised RealizaRecord
	 */
	public RealizaRecord(Integer idaluno, Integer idavaliacao, Date datarealizacao, Byte estado) {
		super(Realiza.REALIZA);

		setValue(0, idaluno);
		setValue(1, idavaliacao);
		setValue(2, datarealizacao);
		setValue(3, estado);
	}
}
