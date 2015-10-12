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
public class F01_Unembedded {
    public static final String DEST = "results/fonts/tutorial/f01_unembedded.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F01_Unembedded().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Vous êtes d'où?"));
        document.add(new Paragraph("À tout à l'heure. À bientôt."));
        document.add(new Paragraph("Je me présente."));
        document.add(new Paragraph("C'est un étudiant."));
        document.add(new Paragraph("Ça va?"));
        document.add(new Paragraph("Il est ingénieur. Elle est médecin."));
        document.add(new Paragraph("C'est une fenêtre."));
        document.add(new Paragraph("Répétez, s'il vous plaît."));
        document.close();
    }
}
