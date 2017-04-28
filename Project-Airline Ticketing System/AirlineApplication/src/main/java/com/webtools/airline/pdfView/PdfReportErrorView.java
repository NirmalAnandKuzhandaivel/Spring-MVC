package com.webtools.airline.pdfView;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportErrorView extends ITextPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Font font_helvetica_16_normal_blue = new Font(Font.HELVETICA, 16, Font.NORMAL, Color.BLUE); 
		Phrase phr1 = new Phrase("Error", font_helvetica_16_normal_blue);
		Phrase phr2 = new Phrase("See the PDF VIEW LATER", font_helvetica_16_normal_blue);
		pdfdoc.add(phr1);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(phr2);
		pdfdoc.add(Chunk.NEWLINE);
		
	}

}
