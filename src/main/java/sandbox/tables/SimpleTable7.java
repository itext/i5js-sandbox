/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29548762/why-is-my-table-not-being-generated-on-my-pdf-file-using-itextsharp
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class SimpleTable7 {
    public static final String DEST = "results/tables/simple_table7.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable7().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 11, BaseColor.BLACK);
        Paragraph docTitle = new Paragraph("UCSC Direct - Direct Payment Form", titleFont);
        document.add(docTitle);
        Font subtitleFont = FontFactory.getFont("Times Roman", 9, BaseColor.BLACK);
        Paragraph subTitle = new Paragraph("(not to be used for reimbursement of services)", subtitleFont);
        document.add(subTitle);
        Font importantNoticeFont = FontFactory.getFont("Courier", 9, BaseColor.RED);
        Paragraph importantNotice = new Paragraph("Important: Form must be filled out in Adobe Reader or Acrobat Professional 8.1 or above. To save completed forms, Acrobat Professional is required. For technical and accessibility assistance, contact the Campus Controller's Office.", importantNoticeFont);
        document.add(importantNotice);

        PdfPTable table = new PdfPTable(10); // the arg is the number of columns
        PdfPCell cell = new PdfPCell(docTitle);
        cell.setColspan(3);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        PdfPCell cellCaveat = new PdfPCell(subTitle);
        cellCaveat.setColspan(2);
        cellCaveat.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cellCaveat);
        PdfPCell cellImportantNote = new PdfPCell(importantNotice);
        cellImportantNote.setColspan(5);
        cellImportantNote.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cellImportantNote);
        document.add(table);
        
        document.add(new Paragraph(20, "This is the same table, created differently", subtitleFont));
        table = new PdfPTable(3);
        table.setWidths(new int[]{3, 2, 5});
        cell.setColspan(1);
        table.addCell(cell);
        cellCaveat.setColspan(1);
        table.addCell(cellCaveat);
        cellImportantNote.setColspan(1);
        table.addCell(cellImportantNote);
        document.add(table);
        
        document.close();
    }

}
