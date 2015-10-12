/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/28564434/why-i-cant-vertically-print-this-string-with-itext
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.VerticalText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class VTExample {

    public static final String DEST = "results/objects/vertical_text.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new VTExample().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BaseFont bf = BaseFont.createFont(
            FONT, BaseFont.IDENTITY_V, BaseFont.NOT_EMBEDDED);
        Font font = new Font(bf, 20);
        VerticalText vt = new VerticalText(writer.getDirectContent());
        vt.setVerticalLayout(559, 806, 770, 29, 18);
        vt.addText(new Phrase("Hello World !!!", font));
        vt.go();
        document.close();
    }
}
