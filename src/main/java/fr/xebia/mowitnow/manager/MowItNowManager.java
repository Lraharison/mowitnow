package fr.xebia.mowitnow.manager;

import java.util.List;

import fr.xebia.mowitnow.dto.MoveDto;
import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.exception.MowItNowException;

public interface MowItNowManager {

	/**
	 * Creer un MowItNowDto.
	 *
	 * @param mowItNowDto the mow it now dto
	 * @throws MowItNowException the mow it now exception
	 */
	public void create(MowItNowDto mowItNowDto) throws MowItNowException;

	/**
	 * Lister tous les MowItNowDto.
	 *
	 * @return the list
	 */
	public List<MowItNowDto> findAll();

	/**
	 * Calcule les coordonnees ou les deplacements des mowers.
	 *
	 * @param mowItNowDto the mow it now dto
	 * @return the move dto
	 */
	public MoveDto calculate(MowItNowDto mowItNowDto);

}
