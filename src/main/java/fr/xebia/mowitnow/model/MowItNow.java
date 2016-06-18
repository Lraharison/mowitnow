package fr.xebia.mowitnow.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TODO : Entité MowItNow.
 * 
 * @author Raharison L
 */
@Entity
@Table(name = "MOWITNOW")
public class MowItNow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	/** Nom de fichier ou nom de d'enregistrement. */
	@Column(name = "NAME", unique = true)
	private String name;

	/** Position superieur du terrain. */
	@Column(name = "UPPER_RIGHT_CORNER_POS")
	private String upRightCornerPos;

	/** Position initiale de la premiere tondeuse. */
	@Column(name = "FIRST_MOWER_POS")
	private String firstMowerPos;

	/** Les actions de la première tondeuse. */
	@Column(name = "FIRST_MOWER_ACTION")
	private String firstMowerAction;

	/** Position initiale de la deuxieme tondeuse. */
	@Column(name = "SECOND_MOWER_POS")
	private String secondMowerPos;

	/** Les actions de la première tondeuse. */
	@Column(name = "SECOND_MOWER_ACTION")
	private String secondMowerAction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpRightCornerPos() {
		return upRightCornerPos;
	}

	public void setUpRightCornerPos(String upRightCornerPos) {
		this.upRightCornerPos = upRightCornerPos;
	}

	public String getFirstMowerPos() {
		return firstMowerPos;
	}

	public void setFirstMowerPos(String firstMowerPos) {
		this.firstMowerPos = firstMowerPos;
	}

	public String getFirstMowerAction() {
		return firstMowerAction;
	}

	public void setFirstMowerAction(String firstMowerAction) {
		this.firstMowerAction = firstMowerAction;
	}

	public String getSecondMowerPos() {
		return secondMowerPos;
	}

	public void setSecondMowerPos(String secondMowerPos) {
		this.secondMowerPos = secondMowerPos;
	}

	public String getSecondMowerAction() {
		return secondMowerAction;
	}

	public void setSecondMowerAction(String secondMowerAction) {
		this.secondMowerAction = secondMowerAction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MowItNow)) {
			return false;
		}
		MowItNow other = (MowItNow) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(name, other.name);
		return equalsBuilder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		hashCodeBuilder.append(name);
		return hashCodeBuilder.toHashCode();
	}

	@Override
	public String toString() {
		ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		toStringBuilder.append("id", id).append("name", name);
		return toStringBuilder.toString();
	}

}
