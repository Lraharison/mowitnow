package fr.xebia.mowitnow.manager;

import static fr.xebia.mowitnow.MowItNowTestHelper.getMowItNow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.xebia.mowitnow.dao.MowItNowDao;
import fr.xebia.mowitnow.dto.MoveDto;
import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.dto.PositionDto;
import fr.xebia.mowitnow.dto.PositionDto.ORIENTATION;
import fr.xebia.mowitnow.exception.MowItNowException;
import fr.xebia.mowitnow.model.MowItNow;

/**
 * class de test de MowItNowManager.
 * 
 * @author Raharison L
 */
@RunWith(MockitoJUnitRunner.class)
public class MowItNowManagerImplTest {

	@Mock
	private MowItNowDao mowItNowDao;

	@InjectMocks
	private MowItNowManagerImpl mowItNowManager = new MowItNowManagerImpl();

	@Test(expected = MowItNowException.class)
	public void testCreateException() throws MowItNowException {
		when(mowItNowDao.findByName(any(String.class))).thenReturn(mock(MowItNow.class));
		mowItNowManager.create(new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG"));

	}

	@Test
	public void testCreate() throws MowItNowException {
		when(mowItNowDao.findByName(any(String.class))).thenReturn(null);
		mowItNowManager.create(new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG"));
		verify(mowItNowDao, times(1)).save(any(MowItNow.class));
	}

	@Test
	public void testFindAll() {
		when(mowItNowDao.findAll())
				.thenReturn(Arrays.asList(getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG"),
						getMowItNow(null, "mower2", "11", "11G", "GGAA", "21G", "AAGG"),
						getMowItNow(1, "mower3", "11", "11G", "GGAA", "21G", "AAGG")));
		List<MowItNowDto> list = mowItNowManager.findAll();
		verify(mowItNowDao, times(1)).findAll();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 3);
	}

	/**
	 * Test calculate 5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA
	 */
	@Test
	public void testCalculate() {
		MowItNowDto mowItNowDto = new MowItNowDto("xebiaTest.txt", "5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
		MoveDto moveDto = mowItNowManager.calculate(mowItNowDto);
		int firstLastIndex = moveDto.getFirstMowerAction().size() - 1;
		int secondLastIndex = moveDto.getSecondMowerAction().size() - 1;
		assertEquals(moveDto.getFirstMowerAction().get(firstLastIndex).getX(), 1);
		assertEquals(moveDto.getFirstMowerAction().get(firstLastIndex).getY(), 3);
		assertEquals(moveDto.getFirstMowerAction().get(firstLastIndex).getOrientation(), ORIENTATION.NORTH);

		assertEquals(moveDto.getSecondMowerAction().get(secondLastIndex).getX(), 5);
		assertEquals(moveDto.getSecondMowerAction().get(secondLastIndex).getY(), 1);
		assertEquals(moveDto.getSecondMowerAction().get(secondLastIndex).getOrientation(), ORIENTATION.EAST);
	}

	@Test
	public void testRotateRight() {
		PositionDto positionDto = new PositionDto(1, 1, ORIENTATION.NORTH);
		mowItNowManager.rotateRight(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.EAST);
		mowItNowManager.rotateRight(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.SOUTH);
		mowItNowManager.rotateRight(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.WEST);
		mowItNowManager.rotateRight(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.NORTH);
	}

	@Test
	public void testRotateLeft() {
		PositionDto positionDto = new PositionDto(1, 1, ORIENTATION.NORTH);
		mowItNowManager.rotateLeft(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.WEST);
		mowItNowManager.rotateLeft(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.SOUTH);
		mowItNowManager.rotateLeft(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.EAST);
		mowItNowManager.rotateLeft(positionDto);
		assertEquals(positionDto.getOrientation(), ORIENTATION.NORTH);
	}

	@Test
	public void testForward() {
		// deplacement Y
		PositionDto initPosition = new PositionDto(1, 1, ORIENTATION.NORTH);
		PositionDto finalPositionDto = new PositionDto();
		PositionDto dim = new PositionDto(5, 5, ORIENTATION.NORTH);
		mowItNowManager.forward(initPosition, finalPositionDto, dim);
		assertEquals(finalPositionDto.getX(), 1);
		assertEquals(finalPositionDto.getY(), 2);

		// Atteinte bord
		initPosition = new PositionDto(1, 5, ORIENTATION.NORTH);
		mowItNowManager.forward(initPosition, finalPositionDto, dim);
		assertEquals(finalPositionDto.getX(), 1);
		assertEquals(finalPositionDto.getY(), 5);

		// deplacement X
		initPosition = new PositionDto(1, 1, ORIENTATION.EAST);
		mowItNowManager.forward(initPosition, finalPositionDto, dim);
		assertEquals(finalPositionDto.getX(), 2);
		assertEquals(finalPositionDto.getY(), 1);
	}

	@Test(expected = MowItNowException.class)
	public void testFindByNameFailed() throws MowItNowException {
		when(mowItNowDao.findByName(any(String.class))).thenReturn(null);
		mowItNowManager.findByName("test");
	}

	@Test
	public void testFindByNameOk() throws MowItNowException {
		when(mowItNowDao.findByName(any(String.class))).thenReturn(mock(MowItNow.class));
		MowItNowDto dto = mowItNowManager.findByName("test");
		assertNotNull(dto);
	}

}
