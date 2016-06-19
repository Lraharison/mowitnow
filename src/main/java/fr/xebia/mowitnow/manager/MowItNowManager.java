package fr.xebia.mowitnow.manager;

import java.util.List;

import fr.xebia.mowitnow.dto.MoveDto;
import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.exception.MowItNowException;

public interface MowItNowManager {

	/**
	 * Creer un MowItNowDto.
	 *
	 * @param mowItNowDto mowItNowDto a creer
	 * @throws MowItNowException mowItNowException
	 */
	public void create(MowItNowDto mowItNowDto) throws MowItNowException;

	/**
	 * Lister tous les MowItNowDto.
	 *
	 * @return the list
	 */
	public List<MowItNowDto> findAll();

	/**
	 * Chercher un MowItNowDto par son nom.
	 *
	 * @return MowItNowDto MowItNowDto trouve par son nom
	 */
	public MowItNowDto findByName(final String name) throws MowItNowException;

	/**
	 * Calcule les coordonnees ou les deplacements des mowers.
	 *
	 * @param MowItNowDto mowItNowDto a calculer
	 * @return MowItNowDto après mouvement
	 */
	public MoveDto calculate(MowItNowDto mowItNowDto);

}
