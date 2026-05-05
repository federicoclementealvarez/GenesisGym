package servlets;

import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import entities.Class;
import entities.People;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ManageClassController;

public class ExportTaughtClassesPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		People teacher = (People) session.getAttribute("user");

		if (teacher == null || !"teacher".equalsIgnoreCase(teacher.getType())) {
			response.sendRedirect("Login.jsp");
			return;
		}

		ManageClassController cont = new ManageClassController();
		ArrayList<Class> taughtClasses = cont.getTaughtClasses(teacher);

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=Clases_Dictadas_" + teacher.getDni() + ".pdf");

		Document document = new Document();
		java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, baos);
			document.open();

			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

			Paragraph title = new Paragraph("Reporte de Clases Dictadas", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(20);
			document.add(title);

			Paragraph teacherInfo = new Paragraph("Profesor: " + teacher.getName() + " " + teacher.getLastName() + " (DNI: " + teacher.getDni() + ")", normalFont);
			teacherInfo.setSpacingAfter(20);
			document.add(teacherInfo);

			for (Class c : taughtClasses) {
				Paragraph classHeader = new Paragraph("Clase: " + c.getActivity().getName() + " - " + c.getDay() + " (" + c.getBeginHour() + " a " + c.getEndHour() + ")", headerFont);
				classHeader.setSpacingBefore(10);
				classHeader.setSpacingAfter(5);
				document.add(classHeader);

				ArrayList<People> attendants = cont.getInscribedInClass(c.getId());

				if (attendants.isEmpty()) {
					document.add(new Paragraph("No hay alumnos inscritos en esta clase.", normalFont));
				} else {
					PdfPTable table = new PdfPTable(3);
					table.setWidthPercentage(100);
					table.setSpacingBefore(5);
					table.setSpacingAfter(10);

					table.addCell(new PdfPCell(new Phrase("DNI", headerFont)));
					table.addCell(new PdfPCell(new Phrase("Nombre", headerFont)));
					table.addCell(new PdfPCell(new Phrase("Apellido", headerFont)));

					for (People p : attendants) {
						table.addCell(new PdfPCell(new Phrase(String.valueOf(p.getDni()), normalFont)));
						table.addCell(new PdfPCell(new Phrase(p.getName(), normalFont)));
						table.addCell(new PdfPCell(new Phrase(p.getLastName(), normalFont)));
					}
					document.add(table);
				}
				document.add(new Paragraph(" ")); // Espaciado entre clases
			}
			
			document.close();
			
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			response.setHeader("Content-Disposition", "attachment; filename=Clases_Dictadas_" + teacher.getDni() + ".pdf");
			
			java.io.OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();

		} catch (DocumentException e) {
			throw new IOException(e.getMessage());
		} finally {
			if (document.isOpen()) {
				document.close();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
