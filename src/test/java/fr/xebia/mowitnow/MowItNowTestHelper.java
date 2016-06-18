package fr.xebia.mowitnow;

import fr.xebia.mowitnow.model.MowItNow;

public class MowItNowTestHelper {

	private MowItNowTestHelper() {
	}

	/**
	 * Generer un entite MowItNow.
	 *
	 * @param id the id
	 * @param name the name
	 * @param upRightCornerPos the up right corner pos
	 * @param firstMowerPos the first mower pos
	 * @param firstMowerAction the first mower action
	 * @param secondMowerPos the second mower pos
	 * @param secondMowerAction the second mower action
	 * @return the mow it now
	 */
	public static MowItNow getMowItNow(Integer id,
			String name,
			String upRightCornerPos,
			String firstMowerPos,
			String firstMowerAction,
			String secondMowerPos,
			String secondMowerAction) {

		MowItNow mowItNow = new MowItNow();
		mowItNow.setId(id);
		mowItNow.setName(name);
		mowItNow.setUpRightCornerPos(upRightCornerPos);
		mowItNow.setFirstMowerAction(firstMowerAction);
		mowItNow.setFirstMowerPos(firstMowerPos);
		mowItNow.setSecondMowerAction(secondMowerAction);
		mowItNow.setSecondMowerPos(secondMowerPos);
		return mowItNow;
	}

}
