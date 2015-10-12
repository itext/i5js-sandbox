/*
 * These examples are written by Bruno Lowagie in the context of an article about fonts.
 */
package sandbox.fonts.tutorial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
public class F02_Unembedded {
    public static final String DEST = "results/fonts/tutorial/f02_unembedded_and_wrong.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F02_Unembedded().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Odkud jste?"));
        document.add(new Paragraph("Uvidíme se za chvilku. Měj se."));
        document.add(new Paragraph("Dovolte, abych se představil."));
        document.add(new Paragraph("To je studentka."));
        document.add(new Paragraph("Všechno v pořádku?"));
        document.add(new Paragraph("On je inženýr. Ona je lékař."));
        document.add(new Paragraph("Toto je okno."));
        document.add(new Paragraph("Zopakujte to prosím."));
        document.close();
    }
}
