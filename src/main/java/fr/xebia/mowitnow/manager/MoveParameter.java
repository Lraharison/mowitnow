package fr.xebia.mowitnow.manager;

import fr.xebia.mowitnow.dto.PositionDto;

public class MoveParameter {
	public PositionDto initPosition;

	public char command;

	public PositionDto dimension;

	public MoveParameter(PositionDto initPosition, char command, PositionDto dimension) {
		this.initPosition = initPosition;
		this.command = command;
		this.dimension = dimension;
	}
}