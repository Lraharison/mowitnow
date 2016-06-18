package fr.xebia.mowitnow.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fr.xebia.mowitnow.MowItNowTestHelper;
import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.model.MowItNow;

/**
 * class de test de MowItNowConverter.
 * 
 * @author Raharison L
 */
public class MowItNowConverterTest {

	@Test
	public void testConvertToDto() {
		MowItNow mowItNow = MowItNowTestHelper.getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNowDto dto = MowItNowConverter.convertToMowItNowDto(mowItNow);
		assertEquals(mowItNow.getName(), dto.getName());
		assertEquals(mowItNow.getUpRightCornerPos(), dto.getUpRightCornerPos());
		assertEquals(mowItNow.getFirstMowerPos(), dto.getFirstMowerPos());
		assertEquals(mowItNow.getFirstMowerAction(), dto.getFirstMowerAction());
		assertEquals(mowItNow.getSecondMowerPos(), dto.getSecondMowerPos());
		assertEquals(mowItNow.getSecondMowerAction(), dto.getSecondMowerAction());
	}

	@Test
	public void testConvertToDtoNull() {
		MowItNow mowItNow = null;
		MowItNowDto mowItNowDto = MowItNowConverter.convertToMowItNowDto(mowItNow);
		assertNull(mowItNowDto);
	}

	@Test
	public void testConvertToDtos() {
		List<MowItNow> mowItNows = Arrays.asList(
				MowItNowTestHelper.getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG"),
				MowItNowTestHelper.getMowItNow(null, "mower2", "11", "11G", "GGAA", "21G", "AAGG"),
				MowItNowTestHelper.getMowItNow(1, "mower3", "11", "11G", "GGAA", "21G", "AAGG"));
		List<MowItNowDto> dtos = MowItNowConverter.convertToMowItNowDto(mowItNows);
		assertEquals(dtos.size(), 3);
		assertEquals(dtos.size(), mowItNows.size());
		assertEquals(mowItNows.get(0).getName(), dtos.get(0).getName());
		assertEquals(mowItNows.get(2).getName(), dtos.get(2).getName());
	}

	@Test
	public void testConvertToDtosNull() {
		List<MowItNow> mowItNows = null;
		List<MowItNowDto> dtos = MowItNowConverter.convertToMowItNowDto(mowItNows);
		assertNotNull(dtos);
		assertTrue(dtos.isEmpty());
	}

	@Test
	public void testConvertToEntity() {
		MowItNowDto mowItNowDto = new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG");
		MowItNow mowItNow = MowItNowConverter.convertToEntity(mowItNowDto);
		assertEquals(mowItNowDto.getName(), mowItNow.getName());
		assertEquals(mowItNowDto.getUpRightCornerPos(), mowItNow.getUpRightCornerPos());
		assertEquals(mowItNowDto.getFirstMowerPos(), mowItNow.getFirstMowerPos());
		assertEquals(mowItNowDto.getFirstMowerAction(), mowItNow.getFirstMowerAction());
		assertEquals(mowItNowDto.getSecondMowerPos(), mowItNow.getSecondMowerPos());
		assertEquals(mowItNowDto.getSecondMowerAction(), mowItNow.getSecondMowerAction());
	}

	@Test
	public void testConvertToEntityNull() {
		MowItNowDto mowItNowDto = null;
		MowItNow mowItNow = MowItNowConverter.convertToEntity(mowItNowDto);
		assertNull(mowItNow);
	}

	@Test
	public void testConvertToEntities() {
		List<MowItNowDto> mowItNowDtos = Arrays.asList(new MowItNowDto("mower1", "11", "11G", "GGAA", "21G", "AAGG"),
				new MowItNowDto("mower2", "11", "11G", "GGAA", "21G", "AAGG"),
				new MowItNowDto("mower3", "11", "11G", "GGAA", "21G", "AAGG"));
		List<MowItNow> mowItNows = MowItNowConverter.convertToEntity(mowItNowDtos);
		assertEquals(mowItNows.size(), 3);
		assertEquals(mowItNowDtos.size(), mowItNows.size());
		assertEquals(mowItNowDtos.get(0).getName(), mowItNows.get(0).getName());
		assertEquals(mowItNowDtos.get(2).getName(), mowItNows.get(2).getName());
	}

	@Test
	public void testConvertToEntitiesNull() {
		List<MowItNowDto> mowItNowDtos = null;
		List<MowItNow> mowItNows = MowItNowConverter.convertToEntity(mowItNowDtos);
		assertNotNull(mowItNows);
		assertTrue(mowItNows.isEmpty());
	}

}
