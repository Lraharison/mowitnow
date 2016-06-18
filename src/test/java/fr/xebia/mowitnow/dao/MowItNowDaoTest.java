package fr.xebia.mowitnow.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.xebia.mowitnow.MowItNowTestHelper;
import fr.xebia.mowitnow.RepositoryConfigurationTest;
import fr.xebia.mowitnow.model.MowItNow;

/**
 * Class de test de MowItNowDao.
 * 
 * @author Raharison L
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryConfigurationTest.class })
public class MowItNowDaoTest {

	/** The mow it now dao. */
	@Autowired
	private MowItNowDao mowItNowDao;

	/** The mow it now. */
	private MowItNow mowItNow;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		mowItNow = MowItNowTestHelper.getMowItNow(null, "mower1", "11", "11G", "GGAA", "21G", "AAGG");
		mowItNowDao.save(mowItNow);
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		mowItNowDao.delete(mowItNow);
	}

	/**
	 * Test find by name.
	 */
	@Test
	public void testFindByName() {
		MowItNow found = mowItNowDao.findByName("mower1");
		MowItNow notFound = mowItNowDao.findByName("notFound");
		assertNotNull(found);
		assertNull(notFound);
	}

}
