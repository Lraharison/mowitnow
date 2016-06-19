package fr.xebia.mowitnow.util;

import java.util.ArrayList;
import java.util.List;

import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.dto.PositionDto;
import fr.xebia.mowitnow.dto.PositionDto.ORIENTATION;
import fr.xebia.mowitnow.model.MowItNow;

/**
 * Classe converter entite dto et dto entite.
 * 
 * @author Raharison L
 */
public class MowItNowConverter {

	private MowItNowConverter() {

	}

	/**
	 * Convert un entite MowItNow en MowItNowDto.
	 *
	 * @param entite
	 * @return dto
	 */

	/**
	 * Convert un entite MowItNow en MowItNowDto.
	 *
	 * @param mowItNow mowItNow a convertir
	 * @return mowItNowDto converti
	 */
	public static MowItNowDto convertToMowItNowDto(final MowItNow mowItNow) {
		MowItNowDto mowItNowDto = null;
		if (mowItNow != null) {
			mowItNowDto = new MowItNowDto(mowItNow.getName(), mowItNow.getUpRightCornerPos(), mowItNow.getFirstMowerPos(),
					mowItNow.getFirstMowerAction(), mowItNow.getSecondMowerPos(), mowItNow.getSecondMowerAction());
		}
		return mowItNowDto;
	}

	/**
	 * Convert une liste d'entite MowItNow en une liste de MowItNowDto.
	 *
	 * @param mowItNows liste d'entite
	 * @return the list dto
	 */
	public static List<MowItNowDto> convertToMowItNowDto(final List<MowItNow> mowItNows) {
		List<MowItNowDto> result = new ArrayList<>();
		if (mowItNows != null) {
			mowItNows.forEach(e -> result.add(convertToMowItNowDto(e)));
		}
		return result;
	}

	/**
	 * Convert un dto en entite.
	 *
	 * @param mowItNowDto dto
	 * @return entite
	 */
	public static MowItNow convertToEntity(final MowItNowDto mowItNowDto) {
		MowItNow mowItNow = null;
		if (mowItNowDto != null) {
			mowItNow = new MowItNow();
			mowItNow.setName(mowItNowDto.getName());
			mowItNow.setUpRightCornerPos(mowItNowDto.getUpRightCornerPos());
			mowItNow.setFirstMowerAction(mowItNowDto.getFirstMowerAction());
			mowItNow.setFirstMowerPos(mowItNowDto.getFirstMowerPos());
			mowItNow.setSecondMowerAction(mowItNowDto.getSecondMowerAction());
			mowItNow.setSecondMowerPos(mowItNowDto.getSecondMowerPos());
		}
		return mowItNow;

	}

	/**
	 * Convert une liste de dto en une liste d'entite.
	 *
	 * @param mowItNowDtos liste dto
	 * @return liste d'entite
	 */
	public static List<MowItNow> convertToEntity(final List<MowItNowDto> mowItNowDtos) {
		List<MowItNow> result = new ArrayList<>();
		if (mowItNowDtos != null) {
			mowItNowDtos.forEach(e -> result.add(convertToEntity(e)));
		}
		return result;
	}

	/**
	 * Convert une chaine en postion.
	 *
	 * @param positionStr the position str
	 * @return the position dto
	 */
	public static PositionDto convertToPositionDto(final String positionStr) {
		PositionDto positionDto = new PositionDto();
		String[] positionArray = positionStr.split(" ");
		positionDto.setX(Integer.parseInt(positionArray[0]));
		positionDto.setY(Integer.parseInt(positionArray[1]));
		String command = positionArray.length < 3
																							? "N" : positionArray[2];
		positionDto.setOrientation(ORIENTATION.getValueString(command));

		return positionDto;

	}

}
