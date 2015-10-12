/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27842872/finding-trouble-to-change-installed-font-using-itext-library
 */
package sandbox.fonts;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class TengwarQuenya1 {

    public static final String DEST = "results/fonts/tengwarquenya1.pdf";
    public static final String FONT = "resources/fonts/QUENCAP1.TTF";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TengwarQuenya1().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        Font f1 = FontFactory.getFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED, 12);
        document.add(new Paragraph("A Hello World PDF document.", f1));
        document.close();
    }
}
