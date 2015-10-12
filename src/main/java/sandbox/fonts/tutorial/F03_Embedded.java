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
public class F03_Embedded {
    public static final String DEST = "results/fonts/tutorial/f03_embedded.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F03_Embedded().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font font = FontFactory.getFont(FONT, "Cp1250", BaseFont.EMBEDDED);
        document.add(new Paragraph("Odkud jste?", font));
        document.add(new Paragraph("Uvid\u00edme se za chvilku. M\u011bj se.", font));
        document.add(new Paragraph("Dovolte, abych se p\u0159edstavil.", font));
        document.add(new Paragraph("To je studentka.", font));
        document.add(new Paragraph("V\u0161echno v po\u0159\u00e1dku?", font));
        document.add(new Paragraph("On je in\u017een\u00fdr. Ona je l\u00e9ka\u0159.", font));
        document.add(new Paragraph("Toto je okno.", font));
        document.add(new Paragraph("Zopakujte to pros\u00edm.", font));
        document.close();
    }
}
