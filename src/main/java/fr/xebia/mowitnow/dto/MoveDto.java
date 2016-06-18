package fr.xebia.mowitnow.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Class qui traite les deplacements des mowers.
 * 
 * @author Raharison L
 */
public class MoveDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PositionDto upRightCornerPos;

	private PositionDto firstMowerPos;

	private List<PositionDto> firstMowerAction;

	private PositionDto secondMowerPos;

	private List<PositionDto> secondMowerAction;

	public PositionDto getUpRightCornerPos() {
		return upRightCornerPos;
	}

	public void setUpRightCornerPos(PositionDto upRightCornerPos) {
		this.upRightCornerPos = upRightCornerPos;
	}

	public PositionDto getFirstMowerPos() {
		return firstMowerPos;
	}

	public void setFirstMowerPos(PositionDto firstMowerPos) {
		this.firstMowerPos = firstMowerPos;
	}

	public List<PositionDto> getFirstMowerAction() {
		return firstMowerAction;
	}

	public void setFirstMowerAction(List<PositionDto> firstMowerAction) {
		this.firstMowerAction = firstMowerAction;
	}

	public PositionDto getSecondMowerPos() {
		return secondMowerPos;
	}

	public void setSecondMowerPos(PositionDto secondMowerPos) {
		this.secondMowerPos = secondMowerPos;
	}

	public List<PositionDto> getSecondMowerAction() {
		return secondMowerAction;
	}

	public void setSecondMowerAction(List<PositionDto> secondMowerAction) {
		this.secondMowerAction = secondMowerAction;
	}

}
