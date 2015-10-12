/*
 * These examples are written by Bruno Lowagie in the context of an article about fonts.
 */
package sandbox.fonts.tutorial;

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

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class F05_Russian_correct_encoding {
    public static final String DEST = "results/fonts/tutorial/f05_russian_correct_encoding.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F05_Russian_correct_encoding().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font font = FontFactory.getFont(FONT, "Cp1251", BaseFont.EMBEDDED);
        document.add(new Paragraph("\u041e\u0442\u043a\u0443\u0434\u0430 \u0442\u044b?", font));
        document.add(new Paragraph("\u0423\u0432\u0438\u0434\u0438\u043c\u0441\u044f \u0432 \u043d\u0435\u043c\u043d\u043e\u0433\u043e. \u0423\u0432\u0438\u0434\u0438\u043c\u0441\u044f.", font));
        document.add(new Paragraph("\u041f\u043e\u0437\u0432\u043e\u043b\u044c\u0442\u0435 \u043c\u043d\u0435 \u043f\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u044c\u0441\u044f.", font));
        document.add(new Paragraph("\u042d\u0442\u043e \u0441\u0442\u0443\u0434\u0435\u043d\u0442.", font));
        document.add(new Paragraph("\u0425\u043e\u0440\u043e\u0448\u043e?", font));
        document.add(new Paragraph("\u041e\u043d \u0438\u043d\u0436\u0435\u043d\u0435\u0440. \u041e\u043d\u0430 \u0434\u043e\u043a\u0442\u043e\u0440.", font));
        document.add(new Paragraph("\u042d\u0442\u043e \u043e\u043a\u043d\u043e.", font));
        document.add(new Paragraph("\u041f\u043e\u0432\u0442\u043e\u0440\u0438\u0442\u0435, \u043f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430.", font));
        document.close();
    }
}
