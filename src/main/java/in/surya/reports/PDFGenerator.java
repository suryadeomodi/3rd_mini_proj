package in.surya.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.surya.response.SearchResponse;

public class PDFGenerator {

	public void generatePDF(List<SearchResponse> records, HttpServletResponse response)
			throws DocumentException, IOException {
		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);
		Paragraph paragraph = new Paragraph("Eligibility Details", font);
		document.add(paragraph);

		PdfPTable pdfPTable = new PdfPTable(9);
		pdfPTable.addCell("S.No");
		pdfPTable.addCell("Holder Name");
		pdfPTable.addCell("Holder SSN");
		pdfPTable.addCell("Plan Name");
		pdfPTable.addCell("Plan Status");
		pdfPTable.addCell("Start Date");
		pdfPTable.addCell("End Date");
		pdfPTable.addCell("Benefit Amount");
		pdfPTable.addCell("Denial Reason");

		int sno = 1;

		for (SearchResponse response2 : records) {
			pdfPTable.addCell(String.valueOf(sno));
			pdfPTable.addCell(response2.getHolderName());
			pdfPTable.addCell(String.valueOf(response2.getHolderSsn()));
			pdfPTable.addCell(response2.getPlanName());
			pdfPTable.addCell(response2.getPlanStatus());
			pdfPTable.addCell(String.valueOf(response2.getStartDate()));
			pdfPTable.addCell(String.valueOf(response2.getEndDate()));
			pdfPTable.addCell(String.valueOf(response2.getBenefitAmt()));
			pdfPTable.addCell(response2.getDenailReason());
			sno++;
		}
		document.add(pdfPTable);
		document.close();
		writer.close();
	}

}
