package in.surya.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import in.surya.bindings.SearchRequest;
import in.surya.reports.ExcelGenerator;
import in.surya.reports.PDFGenerator;
import in.surya.response.SearchResponse;
import in.surya.service.ReportService;

@RestController
public class ReportRestController {
	@Autowired
	private ReportService service;

	@GetMapping("/plan-names")
	public List<String> getPlanNames() {
		List<String> planNames = service.getPlanNames();
		return planNames;
	}

	@GetMapping("/plan-status")
	public List<String> getPlanStatus() {
		List<String> planStatus = service.getPlanStatus();
		return planStatus;
	}

	@PostMapping("/search")
	public List<SearchResponse> SearchPlans(@RequestBody SearchRequest request) {
		List<SearchResponse> responses = service.searchPlans(request);
		return responses;
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.xls";
		response.setHeader(headerKey, headerValue);

		List<SearchResponse> records = service.searchPlans(null);

		ExcelGenerator generator = new ExcelGenerator();
		generator.generateExcel(records, response);

	}

	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse httpResponse) throws DocumentException, IOException {
		
		
		
		httpResponse.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.pdf";
		httpResponse.setHeader(headerKey, headerValue);
		
		List<SearchResponse> records = service.searchPlans(null);
		

		PDFGenerator generator = new PDFGenerator();

		generator.generatePDF(records, httpResponse);

	}

}
