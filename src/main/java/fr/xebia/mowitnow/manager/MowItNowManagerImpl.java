package fr.xebia.mowitnow.manager;

import static fr.xebia.mowitnow.util.ExceptionUtil.loggError;
import static fr.xebia.mowitnow.util.MowItNowConverter.convertToEntity;
import static fr.xebia.mowitnow.util.MowItNowConverter.convertToMowItNowDto;
import static fr.xebia.mowitnow.util.MowItNowConverter.convertToPositionDto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.xebia.mowitnow.dao.MowItNowDao;
import fr.xebia.mowitnow.dto.MoveDto;
import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.dto.PositionDto;
import fr.xebia.mowitnow.dto.PositionDto.ORIENTATION;
import fr.xebia.mowitnow.exception.MowItNowException;
import fr.xebia.mowitnow.model.MowItNow;

/**
 * Classe de service de MowItNow .
 * 
 * @author Raharison L
 */
@Service
@Transactional
public class MowItNowManagerImpl implements MowItNowManager {

	/** The mow it now dao. */
	@Autowired
	private MowItNowDao mowItNowDao;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MowItNowManagerImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(MowItNowDto mowItNowDto) throws MowItNowException {
		MowItNow mowItNow = mowItNowDao.findByName(mowItNowDto.getName());
		if (mowItNow != null) {
			String message = "Le  MowItNow " + mowItNowDto.getName() + " existe déjà";
			loggError(message, LOGGER);
		}
		mowItNow = convertToEntity(mowItNowDto);
		mowItNowDao.save(mowItNow);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MowItNowDto> findAll() {
		List<MowItNow> mowItNows = mowItNowDao.findAll();
		return convertToMowItNowDto(mowItNows);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MoveDto calculate(MowItNowDto mowItNowDto) {
		MoveDto moveDto = new MoveDto();
		PositionDto upRightCornerPos = convertToPositionDto(mowItNowDto.getUpRightCornerPos());
		PositionDto firstMowerPos = convertToPositionDto(mowItNowDto.getFirstMowerPos());
		PositionDto secondMowerPos = convertToPositionDto(mowItNowDto.getSecondMowerPos());
		List<PositionDto> firstMowerAction = new ArrayList<>();
		List<PositionDto> secondMowerAction = new ArrayList<>();

		PositionDto tmp = copyPositionDto(firstMowerPos);
		iteratePositions(tmp, upRightCornerPos, mowItNowDto.getFirstMowerAction(), firstMowerAction);

		tmp = copyPositionDto(secondMowerPos);
		iteratePositions(tmp, upRightCornerPos, mowItNowDto.getSecondMowerAction(), secondMowerAction);

		moveDto.setUpRightCornerPos(upRightCornerPos);
		moveDto.setFirstMowerPos(firstMowerPos);
		moveDto.setFirstMowerAction(firstMowerAction);
		moveDto.setSecondMowerPos(secondMowerPos);
		moveDto.setSecondMowerAction(secondMowerAction);
		return moveDto;

	}

	/**
	 * Iterer les positions à faire
	 *
	 * @param tmp the tmp
	 * @param upRightCornerPos dimension du terrain
	 * @param mowerAction les commandes à faire sous forme de String
	 * @param listToAdd list pour ajouter les poisitions à faire
	 */
	private void iteratePositions(PositionDto tmp,
			PositionDto upRightCornerPos,
			String mowerAction,
			List<PositionDto> listToAdd) {
		for (char c : mowerAction.toCharArray()) {
			tmp = move(tmp, c, upRightCornerPos);
			listToAdd.add(tmp);
		}
	}

	/**
	 * Clone un positionDto.
	 *
	 * @param positionDto the position dto
	 * @return the position dto
	 */
	private PositionDto copyPositionDto(PositionDto positionDto) {
		PositionDto result = new PositionDto();
		result.setX(positionDto.getX());
		result.setY(positionDto.getY());
		result.setOrientation(positionDto.getOrientation());
		return result;
	}

	/**
	 * Action suite à une commande.
	 *
	 * @param initPosition position initiale
	 * @param command la commande
	 * @param dimension le dimension du terrain
	 * @return the position dto
	 */
	private PositionDto move(final PositionDto initPosition, final char command, final PositionDto dimension) {
		PositionDto finalPosition = new PositionDto();
		finalPosition.setOrientation(initPosition.getOrientation());
		finalPosition.setX(initPosition.getX());
		finalPosition.setY(initPosition.getY());
		switch (command) {
		case 'D':
			rotateRight(finalPosition);
			break;
		case 'G':
			rotateLeft(finalPosition);
			break;
		case 'A':
			forward(initPosition, finalPosition, dimension);
			break;
		}

		return finalPosition;

	}

	/**
	 * Rotate right.
	 *
	 * @param positionDto the position dto
	 */
	private void rotateRight(PositionDto positionDto) {
		switch (positionDto.getOrientation()) {
		case NORTH:
			positionDto.setOrientation(ORIENTATION.EAST);
			break;
		case EAST:
			positionDto.setOrientation(ORIENTATION.SOUTH);
			break;
		case SOUTH:
			positionDto.setOrientation(ORIENTATION.WEST);
			break;
		case WEST:
			positionDto.setOrientation(ORIENTATION.NORTH);
			break;
		default:
			break;
		}
	}

	/**
	 * Rotate left.
	 *
	 * @param positionDto the position dto
	 */
	private void rotateLeft(PositionDto positionDto) {
		switch (positionDto.getOrientation()) {
		case NORTH:
			positionDto.setOrientation(ORIENTATION.WEST);
			break;
		case WEST:
			positionDto.setOrientation(ORIENTATION.SOUTH);
			break;
		case SOUTH:
			positionDto.setOrientation(ORIENTATION.EAST);
			break;
		case EAST:
			positionDto.setOrientation(ORIENTATION.NORTH);
			break;
		}
	}

	/**
	 * Avancer.
	 *
	 * @param initPosition the init position
	 * @param finalPositionDto the final position dto
	 * @param dimension the dimension
	 */
	private void forward(final PositionDto initPosition, PositionDto finalPositionDto, final PositionDto dimension) {
		int x = initPosition.getX();
		int y = initPosition.getY();
		switch (initPosition.getOrientation()) {
		case NORTH:
			y++;
			break;
		case WEST:
			x--;
			break;
		case SOUTH:
			y--;
			break;
		case EAST:
			x++;
			break;
		}

		x = (x >= 0 && x <= dimension.getX())
																					? x : initPosition.getX();
		y = (y >= 0 && y <= dimension.getY())
																					? y : initPosition.getY();
		finalPositionDto.setX(x);
		finalPositionDto.setY(y);

	}

}
