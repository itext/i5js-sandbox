/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/37246838
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class RotatedCell {
    public static final String DEST = "results/tables/rotated_cell.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RotatedCell().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(8);
        for (int i = 0; i < 8; i++) {
            PdfPCell cell =
                new PdfPCell(new Phrase(String.format("May %s, 2016", i + 15)));
            cell.setRotation(90);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }
        for(int i = 0; i < 16; i++){
            table.addCell("hi");
        }
        document.add(table);
        document.close();
    }
    
}
