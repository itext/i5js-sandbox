/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/33286841/stop-itext-table-from-spliting-on-new-page
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class Splitting2 {
    public static final String DEST = "results/tables/splitting2.pdf";
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Splitting2().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph p = new Paragraph("Test");
        PdfPTable table = new PdfPTable(2);
        for (int i = 1; i < 6; i++) {
            table.addCell("key " + i);
            table.addCell("value " + i);
        }
        for (int i = 0; i < 40; i++) {
            document.add(p);
        }
        document.add(table);
        for (int i = 0; i < 38; i++) {
            document.add(p);
        }
        table.setKeepTogether(true);
        document.add(table);
        document.close();
    }
}
