/**
 * 
 */
package com.star.sud.csv.service;

import com.star.sud.csv.dto.EmployeeDetails;
import com.star.sud.csv.status.CSVFileGenStatus;

/**
 * @author Sudarshan
 *
 */
public interface CSVFileGenerationService {

	/**
	 * @param empDetails
	 * @return
	 */
	CSVFileGenStatus writeXlFile(EmployeeDetails empDetails);

	/**
	 * @param filePath
	 */
	void downloadCsvFile(String filePath);

}
