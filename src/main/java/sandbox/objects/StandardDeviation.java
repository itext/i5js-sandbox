/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/30876926/itext-std-deviation-symbol-%CF%83-not-printing
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class StandardDeviation {

    public static final String DEST = "results/objects/standard_deviation.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new StandardDeviation().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("The standard deviation symbol doesn't exist in Helvetica."));
        Font symbol = new Font(FontFamily.SYMBOL);
        Paragraph p = new Paragraph("So we use the Symbol font: ");
        p.add(new Chunk("s", symbol));
        document.add(p);
        document.close();
    }
    
}
