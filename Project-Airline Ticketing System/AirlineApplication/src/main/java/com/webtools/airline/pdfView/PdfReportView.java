package com.webtools.airline.pdfView;

import java.awt.Color;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.webtools.airline.model.Order;
import com.webtools.airline.model.Passenger;
import com.webtools.airline.model.Ticket;
import com.lowagie.text.Table;


public class PdfReportView extends ITextPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter arg2, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Order o=(Order)model.get("customerOrder");
		Font font_helvetica_16_normal_blue = new Font(Font.HELVETICA, 16, Font.NORMAL, Color.BLUE); 
		Table table = new Table(3);
		table.addCell("OrderID");
		table.addCell("No of Passenger");
		table.addCell("Price");
		
		Phrase phr1 = new Phrase("Order", font_helvetica_16_normal_blue);
		Phrase phr2 = new Phrase("Passenger Details", font_helvetica_16_normal_blue);
		Phrase phr3 = new Phrase("Tickets", font_helvetica_16_normal_blue);
		
		table.addCell(Integer.toString(o.getOrderID()));
		table.addCell(Integer.toString(o.getNoOfPassengers()));
		table.addCell(Float.toString(o.getPrice()));
		
		Table table1 = new Table(5);
		table1.addCell("First Name");
		table1.addCell("Last Name");
		table1.addCell("Passenger Age");
		table1.addCell("Passenger ID");
		table1.addCell("Passenger Proof");
		
		for(Passenger p:o.getPassengerList()){
			table1.addCell(p.getFirstName());
			table1.addCell(p.getLastName());
			table1.addCell(Integer.toString(p.getAge()));
			table1.addCell(Integer.toString(p.getPassengerID()));
			table1.addCell(p.getIdProof());
		}
		
		Table table2 = new Table(6);
		table2.addCell("Ticket ID");
		table2.addCell("Flight ID");
		table2.addCell("From Place");
		table2.addCell("To Place");
		table2.addCell("Ticket Type");
		table2.addCell("Ticket Price");
		
		for(Ticket t:o.getTickets()){
			table2.addCell(Integer.toString(t.getTicketID()));
			table2.addCell(t.getFlightID());
			table2.addCell(t.getFromPlace());
			table2.addCell(t.getToPlace());
			table2.addCell(t.getTicketType());
			table2.addCell(Float.toString(t.getPrice()));
		}
		
		pdfdoc.add(phr1);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(table);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(phr2);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(table1);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(phr3);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(table2);
		
	}

	

}
