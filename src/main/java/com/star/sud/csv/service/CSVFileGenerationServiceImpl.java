/**
 * 
 */
package com.star.sud.csv.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.csv.dto.EmployeeDetails;
import com.star.sud.csv.status.CSVFileGenStatus;
import com.star.sud.csv.status.CSVFileGenStatus.STATUS;

/**
 * @author Sudarshan
 *
 */
@Service("xlFileGenerationService")
public class CSVFileGenerationServiceImpl implements CSVFileGenerationService {

	// Static Attributes
	//////////////////////
	private static final Logger log = Logger.getLogger(CSVFileGenerationServiceImpl.class);

	@Autowired
	protected HttpServletResponse response;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.xl.service.XlFileGenerationService#writeXlFile(com.star.sud.xl.
	 * dto.EmployeeDetails)
	 */
	public CSVFileGenStatus writeXlFile(EmployeeDetails empDetails) {
		log.debug("XlFileGenStatus");
		CSVFileGenStatus status = new CSVFileGenStatus();

		FileOutputStream out = null;

		try {

			if (null == empDetails)
				throw new Exception("empDetails param is null or empty");

			File temp = File.createTempFile("CSV_File_generation", ".csv");

			out = new FileOutputStream(temp);

			String header = "Employee Id,Employee Name, Employee Designation, Employee Department, Employee Address\n";
			out.write(header.getBytes());

			String content = empDetails.getEmpId() + "," + empDetails.getEmpName() + "," + empDetails.getEmpDesign()
					+ "," + empDetails.getEmpDept() + "," + empDetails.getEmpAddress();

			out.write(content.toString().getBytes());

			status.setResult(temp.getAbsolutePath());
			status.setStatus(STATUS.SUCCESS);

		} catch (Exception e) {
			log.error("writeXlFile", e);
			status.setStatus(STATUS.FAILED);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (Exception ex) {
					log.error("error closing output stream");
				}
			}

		}
		return status;
	}

	public void downloadCsvFile(String filePath) {

		ServletOutputStream os = null;
		try {

			if (null == filePath)
				throw new Exception("filePath para is null or empty");

			File file = new File(filePath);

			os = response.getOutputStream();

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() + ".csv");

			byte[] imgByArry = Files.readAllBytes(file.toPath());
			os.write(imgByArry);

		} catch (Exception e) {
			log.error("downloadCsvFile", e);
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (Exception ex) {
					log.error("error closing output stream");
				}
			}

		}
	}

}
