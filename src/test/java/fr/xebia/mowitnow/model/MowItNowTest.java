package fr.xebia.mowitnow.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.xebia.mowitnow.MowItNowTestHelper;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * Class de test de l'entite MowItNow.
 * 
 * @author Raharison L
 */
public class MowItNowTest {

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(MowItNow.class).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE)
				.verify();
	}

	@Test
	public void testHashcode() {
		MowItNow mowItNow1 = MowItNowTestHelper.getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNow mowItNow2 = MowItNowTestHelper.getMowItNow(null, "mower2", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNow mowItNow3 = MowItNowTestHelper.getMowItNow(1, "mower1", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNow mowItNow4 = MowItNowTestHelper.getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG");
		assertNotEquals(mowItNow1.hashCode(), mowItNow2.hashCode());
		assertEquals(mowItNow1.hashCode(), mowItNow3.hashCode());
		assertEquals(mowItNow3.hashCode(), mowItNow4.hashCode());
	}

	@Test
	public void testGetterSetter() {
		MowItNow mowItNow = new MowItNow();
		mowItNow.setId(1);
		mowItNow.setName("mower1");
		mowItNow.setUpRightCornerPos("11");
		mowItNow.setFirstMowerAction("GGG");
		mowItNow.setFirstMowerPos("11G");
		mowItNow.setSecondMowerAction("GGA");
		mowItNow.setSecondMowerPos("22G");
		assertNotNull(mowItNow.getId());
		assertNotNull(mowItNow.getUpRightCornerPos());
		assertNotNull(mowItNow.getFirstMowerAction());
		assertNotNull(mowItNow.getFirstMowerPos());
		assertNotNull(mowItNow.getSecondMowerAction());
		assertNotNull(mowItNow.getSecondMowerPos());
	}

	@Test
	public void testToString() {
		MowItNow mowItNow = MowItNowTestHelper.getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG");
		assertNotNull(mowItNow.toString());
		assertTrue(mowItNow.toString().contains("mower1"));
	}

}
