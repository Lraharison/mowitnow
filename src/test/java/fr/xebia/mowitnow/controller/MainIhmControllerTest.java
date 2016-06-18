package fr.xebia.mowitnow.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.exception.MowItNowException;
import fr.xebia.mowitnow.manager.MowItNowManager;

/**
 * Test Ihm controller.
 * 
 * @author Raharison L
 */
public class MainIhmControllerTest {

	@Mock
	private MowItNowManager mowItNowManager;

	@InjectMocks
	private MainIhmController mainIhmController = new MainIhmController();

	@Test(expected = MowItNowException.class)
	public void testValidateMowItNowDtoErrorDimension() throws MowItNowException {
		mainIhmController
				.validateMowItNowDto(new MowItNowDto("file1", "5 K", "1 2 N", "GAGAGAGAG", "3 3 E", "AADAADADDA"));

	}

	@Test(expected = MowItNowException.class)
	public void testValidateMowItNowDtoErrorFirstPosition() throws MowItNowException {
		mainIhmController
				.validateMowItNowDto(new MowItNowDto("file1", "5 5", "1 2 X", "GAGAGAGAG", "3 3 E", "AADAADADDA"));

	}

	@Test(expected = MowItNowException.class)
	public void testValidateMowItNowDtoErrorFirstActions() throws MowItNowException {
		mainIhmController
				.validateMowItNowDto(new MowItNowDto("file1", "5 5", "1 2 N", "GAGAGAGXG", "3 3 E", "AADAADADDA"));

	}

	@Test(expected = MowItNowException.class)
	public void testValidateMowItNowDtoErrorSecondPosition() throws MowItNowException {
		mainIhmController
				.validateMowItNowDto(new MowItNowDto("file1", "5 5", "1 2 N", "GAGAGAGAG", "3 3 X", "AADAADADDA"));

	}

	@Test(expected = MowItNowException.class)
	public void testValidateMowItNowDtoErrorSecondActions() throws MowItNowException {
		mainIhmController
				.validateMowItNowDto(new MowItNowDto("file1", "5 5", "1 2 N", "GAGAGAGAG", "3 3 E", "AADAADADXA"));

	}

	@Test
	public void testValidateMowItNowDtoOk() throws MowItNowException {
		mainIhmController
				.validateMowItNowDto(new MowItNowDto("file1", "5 5", "1 2 N", "GAGAGAGAG", "3 3 E", "AADAADADAA"));

	}

}
