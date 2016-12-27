/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/40607960
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class TableWithSeparator {
    public static final String DEST = "results/tables/table_separator.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableWithSeparator().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.addCell(getCell1());
        table.addCell(getCell2());
        table.addCell(getCell3());
        table.addCell(getCell4());
        document.add(table);
        document.close();
    }

    public PdfPCell getCell1() {
        PdfPCell cell = new PdfPCell();
        Paragraph p1 = new Paragraph("My fantastic data");
        cell.addElement(p1);
        LineSeparator ls = new LineSeparator();
        cell.addElement(ls);
        Paragraph p2 = new Paragraph("Other data");
        cell.addElement(p2);
        return cell;
    }

    public PdfPCell getCell2() {
        PdfPCell cell = new PdfPCell();
        Paragraph p1 = new Paragraph("My fantastic data");
        cell.addElement(p1);
        LineSeparator ls = new LineSeparator();
        ls.setOffset(-4);
        cell.addElement(ls);
        Paragraph p2 = new Paragraph("Other data");
        cell.addElement(p2);
        return cell;
    }

    public PdfPCell getCell3() {
        PdfPCell cell = new PdfPCell();
        Paragraph p1 = new Paragraph("My fantastic data");
        cell.addElement(p1);
        LineSeparator ls = new LineSeparator();
        ls.setOffset(-8);
        cell.addElement(ls);
        Paragraph p2 = new Paragraph("Other data");
        p2.setLeading(25);
        cell.addElement(p2);
        return cell;
    }

    public PdfPCell getCell4() {
        PdfPCell cell = new PdfPCell();
        Paragraph p1 = new Paragraph("My fantastic data");
        p1.setSpacingAfter(20);
        cell.addElement(p1);
        LineSeparator ls = new LineSeparator();
        cell.addElement(ls);
        Paragraph p2 = new Paragraph("Other data");
        p2.setSpacingBefore(10);
        cell.addElement(p2);
        return cell;
    }
}
