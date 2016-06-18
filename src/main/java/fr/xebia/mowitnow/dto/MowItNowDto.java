package fr.xebia.mowitnow.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MowItNowDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/** Position superieur du terrain. */
	private String upRightCornerPos;

	/** Position initiale de la premiere tondeuse. */
	private String firstMowerPos;

	/** Les actions de la première tondeuse. */
	private String firstMowerAction;

	/** Position initiale de la deuxieme tondeuse. */
	private String secondMowerPos;

	/** Les actions de la première tondeuse. */
	private String secondMowerAction;

	public MowItNowDto() {

	}

	public MowItNowDto(String name,
			String upRightCornerPos,
			String firstMowerPos,
			String firstMowerAction,
			String secondMowerPos,
			String secondMowerAction) {
		super();
		this.name = name;
		this.upRightCornerPos = upRightCornerPos;
		this.firstMowerPos = firstMowerPos;
		this.firstMowerAction = firstMowerAction;
		this.secondMowerPos = secondMowerPos;
		this.secondMowerAction = secondMowerAction;
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
		if (!(obj instanceof MowItNowDto)) {
			return false;
		}
		MowItNowDto other = (MowItNowDto) obj;
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
		toStringBuilder.append("name", name).append("upRightCornerPos", upRightCornerPos)
				.append("firstMowerPos", firstMowerPos).append("firstMowerAction", firstMowerAction)
				.append("secondMowerPos", secondMowerPos).append("secondMowerAction", secondMowerAction);
		return toStringBuilder.toString();
	}

}
