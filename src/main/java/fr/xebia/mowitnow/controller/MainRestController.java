package fr.xebia.mowitnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.xebia.mowitnow.dto.MoveDto;
import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.exception.MowItNowException;
import fr.xebia.mowitnow.manager.MowItNowManager;

/**
 * Main rest controller class.
 * 
 * @author Raharison L
 */
@RestController
public class MainRestController {

	@Autowired
	private MowItNowManager mowItNowManager;

	@RequestMapping(value = "/calculate", method = RequestMethod.GET)
	public MoveDto findByName(@RequestParam(value = "name") String name) {
		MowItNowDto mowItNowDto = null;
		try {
			mowItNowDto = mowItNowManager.findByName(name);
		} catch (MowItNowException e) {
			return null;
		}
		return mowItNowManager.calculate(mowItNowDto);

	}

}
