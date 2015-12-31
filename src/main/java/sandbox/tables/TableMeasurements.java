/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/34539028
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class TableMeasurements {
    public static final String DEST = "results/tables/table_measurements.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableMeasurements().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(10);
        table.setTotalWidth(Utilities.millimetersToPoints(100));
        table.setLockedWidth(true);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.addCell(getCell(10));
        table.addCell(getCell(5));
        table.addCell(getCell(3));
        table.addCell(getCell(2));
        table.addCell(getCell(3));
        table.addCell(getCell(5));
        table.addCell(getCell(1));
        table.completeRow();
        document.add(table);
        document.close();
    }
    
    private PdfPCell getCell(int cm) {
        PdfPCell cell = new PdfPCell();
        cell.setColspan(cm);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        Paragraph p = new Paragraph(
                String.format("%smm", 10 * cm),
                new Font(Font.FontFamily.HELVETICA, 8));
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        return cell;
    }
}
