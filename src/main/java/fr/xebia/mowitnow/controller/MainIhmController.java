package fr.xebia.mowitnow.controller;

import static fr.xebia.mowitnow.util.MowItNowConverter.convertToPositionDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.xebia.mowitnow.dto.MowItNowDto;
import fr.xebia.mowitnow.dto.PositionDto;
import fr.xebia.mowitnow.exception.MowItNowException;
import fr.xebia.mowitnow.manager.MowItNowManager;

/**
 * Main ihm controller class.
 * 
 * @author Raharison L
 */
@Controller
public class MainIhmController {

	@Autowired
	private MowItNowManager mowItNowManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<MowItNowDto> dtos = mowItNowManager.findAll();
		model.addAttribute("objects", dtos);
		return "home";
	}

	@RequestMapping(value = "/postFile", method = RequestMethod.POST)
	public String handleUploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			return redirectError(redirectAttributes, "Le fichier est vide");
		}
		List<String> lines = null;
		try {
			lines = new BufferedReader(new InputStreamReader(file.getInputStream())).lines().collect(Collectors.toList());
		} catch (IOException e) {
			return redirectError(redirectAttributes, "Probleme sur la lecture du fichier : " + e.getMessage());
		}
		if (lines == null) {
			return redirectError(redirectAttributes, "Impossible de lire le fichier");
		}
		if (lines.size() != 5) {
			return redirectError(redirectAttributes, "Fichier invalide");
		}

		MowItNowDto mowItNowDto = new MowItNowDto(file.getOriginalFilename(), lines.get(0), lines.get(1), lines.get(2),
				lines.get(3), lines.get(4));
		try {
			validateMowItNowDto(mowItNowDto);
		} catch (MowItNowException e) {
			return redirectError(redirectAttributes, "Fichier invalide : " + e.getMessage());
		}
		try {
			mowItNowManager.create(mowItNowDto);
		} catch (MowItNowException e) {
			return redirectError(redirectAttributes, "Probleme de sauvegarde : " + e.getMessage());
		}
		return "redirect:/";
	}

	private String redirectError(RedirectAttributes redirectAttributes, String message) {
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/";
	}

	/**
	 * Validate dto requete.
	 *
	 * @param mowItNowDto the mow it now dto
	 * @throws MowItNowException the mow it now exception
	 */
	protected void validateMowItNowDto(MowItNowDto mowItNowDto) throws MowItNowException {
		if (mowItNowDto == null) {
			throw new MowItNowException("La requete est null");
		}
		if (StringUtils.isEmpty(mowItNowDto.getUpRightCornerPos())) {
			throw new MowItNowException("La dimension est vide");
		}
		if (StringUtils.isEmpty(mowItNowDto.getFirstMowerPos())) {
			throw new MowItNowException("La premiere postion est vide");
		}
		if (StringUtils.isEmpty(mowItNowDto.getFirstMowerAction())) {
			throw new MowItNowException("La Liste des mouvements de la premiere mower est vide");
		}
		if (StringUtils.isEmpty(mowItNowDto.getSecondMowerPos())) {
			throw new MowItNowException("La seconde postion est vide");
		}
		if (StringUtils.isEmpty(mowItNowDto.getSecondMowerAction())) {
			throw new MowItNowException("La Liste des mouvements de la deuxieme mower est vide");
		}

		try {
			PositionDto upRightCornerPos = convertToPositionDto(mowItNowDto.getUpRightCornerPos());
			PositionDto firstMowerPos = convertToPositionDto(mowItNowDto.getFirstMowerPos());
			PositionDto secondMowerPos = convertToPositionDto(mowItNowDto.getSecondMowerPos());
			if (upRightCornerPos.getOrientation() == null) {
				throw new MowItNowException(
						"Probleme de conversion du contenu du fichier " + mowItNowDto.getUpRightCornerPos());
			}
			if (firstMowerPos.getOrientation() == null) {
				throw new MowItNowException("Probleme de conversion du contenu du fichier " + mowItNowDto.getFirstMowerPos());
			}
			if (secondMowerPos.getOrientation() == null) {
				throw new MowItNowException("Probleme de conversion du contenu du fichier " + mowItNowDto.getSecondMowerPos());
			}
		} catch (RuntimeException e) {
			throw new MowItNowException("Probleme de conversion du contenu du fichier " + e.getMessage());
		}

		if (!mowItNowDto.getFirstMowerAction().chars().allMatch(x -> Arrays.asList('D', 'G', 'A').contains((char) x))) {
			throw new MowItNowException("Probleme de conversion du contenu du fichier " + mowItNowDto.getFirstMowerAction());
		}
		if (!mowItNowDto.getSecondMowerAction().chars().allMatch(x -> Arrays.asList('D', 'G', 'A').contains((char) x))) {
			throw new MowItNowException(
					"Probleme de conversion du contenu du fichier " + mowItNowDto.getSecondMowerAction());
		}

	}

}
