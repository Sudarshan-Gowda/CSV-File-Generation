/**
 * 
 */
package com.star.sud.csv.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.star.sud.csv.dto.EmployeeDetails;
import com.star.sud.csv.service.CSVFileGenerationService;
import com.star.sud.csv.status.CSVFileGenStatus;
import com.star.sud.csv.status.CSVFileGenStatus.STATUS;

/**
 * @author Sudarshan
 *
 */
@Controller
public class CSVFileGenerationController {

	// Static Attributes
	//////////////////////
	public static final Logger log = Logger.getLogger(CSVFileGenerationController.class);

	// Attributes
	/////////////////
	@Autowired
	@Qualifier("xlFileGenerationService")
	private CSVFileGenerationService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLandingPage(Model model) {
		try {

			EmployeeDetails empDetails = new EmployeeDetails();
			model.addAttribute("empDetails", empDetails);

		} catch (Exception e) {
			log.error("getLandingPage", e);
		}
		return "home";
	}

	@RequestMapping(value = "/generateXlFile", method = RequestMethod.POST)
	public String generateXlFile(Model model, @ModelAttribute EmployeeDetails empDetails) {
		log.debug("generateXlFile");
		try {

			CSVFileGenStatus status = service.writeXlFile(empDetails);

			if (status != null && status.getStatus().equals(STATUS.SUCCESS)) {
				model.addAttribute("msgsuccess", "Successfully generated!!");
				model.addAttribute("isDisabled", Boolean.TRUE);
				model.addAttribute("filePath", status.getResult());
			} else {
				model.addAttribute("msgdanger", "Failed to generate QR code!!");
				model.addAttribute("isDisabled", Boolean.FALSE);
			}

			model.addAttribute("empDetails", empDetails);

		} catch (Exception e) {
			log.error("generateXlFile", e);
		}
		return "home";
	}

	@RequestMapping(value = "/download")
	public String downloadFile(Model model, @ModelAttribute EmployeeDetails empDetails) {
		log.debug("downloadFile");
		try {

			if (empDetails != null && empDetails.getFilePath() != null)
				service.downloadCsvFile(empDetails.getFilePath());
			else {
				model.addAttribute("msgdanger", "Failed to generate QR code!!");
				model.addAttribute("isDisabled", Boolean.FALSE);
			}
			model.addAttribute("empDetails", empDetails);

		} catch (Exception e) {
			log.error("downloadFile", e);
		}
		return "home";
	}

}
