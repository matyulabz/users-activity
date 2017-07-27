package com.users.reports;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.users.entities.Task;
import com.users.entities.User;

public enum GeneratePdfReport {
	
	INSTANCE;
	
	public ByteArrayInputStream userTaskReport(User user) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();

			document.add(new Paragraph(user.getFirstName() + user.getLastName()));
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			float[] columnWidths = { 1f, 1f, 1f, 1f, 1f };
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Task"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Project"));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Date Created"));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Done"));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Date Done"));
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);

			Set<Task> tasks = user.getTasks();

			for (Task task : tasks) {

				Date dateCreated = new Date(task.getDateCreated());
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String dateC = format.format(dateCreated);
				String doneText = "False";
				Date dateDone = new Date(task.getDateDone());
				String dateD = "n/a";
				if (task.isDone()) {
					doneText = "True";
					dateD = format.format(dateDone);
				}

				PdfPCell taskCell = new PdfPCell(new Paragraph(task.getName()));
				taskCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				taskCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell projectCell = new PdfPCell(new Paragraph(task.getProject().getName()));
				projectCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				projectCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell createdDateCell = new PdfPCell(new Paragraph(dateC));
				createdDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell doneCell = new PdfPCell(new Paragraph(doneText));
				doneCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				doneCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell doneDateCell = new PdfPCell(new Paragraph(dateD));
				doneDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				doneDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				table.addCell(taskCell);
				table.addCell(projectCell);
				table.addCell(createdDateCell);
				table.addCell(doneCell);
				table.addCell(doneDateCell);
			}

			document.add(table);
			document.close();
			writer.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
