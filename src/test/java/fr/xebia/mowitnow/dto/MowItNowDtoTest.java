package fr.xebia.mowitnow.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * Class de test du DTO MowItNowDto.
 * 
 * @author Raharison L
 */
public class MowItNowDtoTest {
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(MowItNowDto.class).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE)
				.verify();
	}

	@Test
	public void testHashcode() {
		MowItNowDto mowItNow1 = new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNowDto mowItNow2 = new MowItNowDto("mower2", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNowDto mowItNow3 = new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNowDto mowItNow4 = new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG");
		assertNotEquals(mowItNow1.hashCode(), mowItNow2.hashCode());
		assertEquals(mowItNow1.hashCode(), mowItNow3.hashCode());
		assertEquals(mowItNow3.hashCode(), mowItNow4.hashCode());
	}

	@Test
	public void testGetterSetter() {
		MowItNowDto mowItNow = new MowItNowDto();
		mowItNow.setName("mower1");
		mowItNow.setUpRightCornerPos("11");
		mowItNow.setFirstMowerAction("GGG");
		mowItNow.setFirstMowerPos("11G");
		mowItNow.setSecondMowerAction("GGA");
		mowItNow.setSecondMowerPos("22G");
		assertNotNull(mowItNow.getUpRightCornerPos());
		assertNotNull(mowItNow.getFirstMowerAction());
		assertNotNull(mowItNow.getFirstMowerPos());
		assertNotNull(mowItNow.getSecondMowerAction());
		assertNotNull(mowItNow.getSecondMowerPos());
	}

	@Test
	public void testToString() {
		MowItNowDto mowItNow = new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG");
		assertNotNull(mowItNow.toString());
		assertTrue(mowItNow.toString().contains("mower1"));
	}

}
