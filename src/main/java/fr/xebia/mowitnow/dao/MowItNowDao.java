package fr.xebia.mowitnow.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.xebia.mowitnow.model.MowItNow;

public interface MowItNowDao extends JpaRepository<MowItNow, Integer> {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the mow it now
	 */
	public MowItNow findByName(String name);

}
