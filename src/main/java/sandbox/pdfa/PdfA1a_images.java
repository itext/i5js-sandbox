/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27294392/itext-create-pdf-a-1a-with-images
 * It will only work starting with iText 5.5.4
 */
package sandbox.pdfa;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class PdfA1a_images {
    final float MARGIN_OF_ONE_CM = 28.8f;
    public static final String DEST = "results/pdfa/pdf_a1a_images.pdf";
    public static final String FONT = "resources/fonts/OpenSans-Regular.ttf";
    public static final String LOGO = "resources/images/hero.jpg";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfA1a_images().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Font font = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Document document = new com.itextpdf.text.Document(PageSize.A4,
                MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM);
        PdfAWriter writer = PdfAWriter.getInstance(document,
                new FileOutputStream(DEST), PdfAConformanceLevel.PDF_A_1A);
        document.addAuthor("Author");
        document.addSubject("Subject");
        document.addLanguage("nl-nl");
        document.addCreationDate();
        document.addCreator("Creator");
        document.addTitle("title");
        writer.setTagged();
        writer.createXmpMetadata();
        document.open();
        File file = new File("resources/data/sRGB_CS_profile.icm");
        ICC_Profile icc = ICC_Profile
                .getInstance(new FileInputStream(file));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
        Paragraph element = new Paragraph("Hello World", font);
        document.add(element);
        Image logoImage = Image.getInstance(LOGO);
        logoImage.setAccessibleAttribute(PdfName.ALT, new PdfString("Logo"));
        document.add(logoImage);
        document.close();
    }
}