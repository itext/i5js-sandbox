/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/30911216/how-to-re-arrange-pages-in-pdf-using-itext
 */
package sandbox.stamper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class ReorderPages {
    public static final String DEST = "results/stamper/reordered.pdf";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ReorderPages().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        for (int i = 1; i < 17; i++) {
            document.add(new Paragraph(String.format("Page %s", i)));
            document.newPage();
        }
        document.close();
        PdfReader reader = new PdfReader(baos.toByteArray());
        int startToc = 13;
        int n = reader.getNumberOfPages();
        reader.selectPages(String.format("1,%s-%s, 2-%s, %s", startToc, n-1, startToc - 1, n));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
    }
}
