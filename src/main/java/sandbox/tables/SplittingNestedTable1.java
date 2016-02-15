/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/35356847
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
/**
 * @author Bruno Lowagie (iText Software)
 */
public class SplittingNestedTable1 {
    public static final String DEST = "results/tables/splitting_nested_table1.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SplittingNestedTable1().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(new Rectangle(300, 150));
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Table with setSplitLate(true):"));
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph("G"));
        cell.addElement(new Paragraph("R"));
        cell.addElement(new Paragraph("O"));
        cell.addElement(new Paragraph("U"));
        cell.addElement(new Paragraph("P"));
        table.addCell(cell);
        PdfPTable inner = new PdfPTable(1);
        inner.addCell("row 1");
        inner.addCell("row 2");
        inner.addCell("row 3");
        inner.addCell("row 4");
        inner.addCell("row 5");
        cell = new PdfPCell(inner);
        cell.setPadding(0);
        table.addCell(cell);
        document.add(table);
        document.newPage();
        document.add(new Paragraph("Table with setSplitLate(false):"));
        table.setSplitLate(false);
        document.add(table);
        document.close();
    }
}
