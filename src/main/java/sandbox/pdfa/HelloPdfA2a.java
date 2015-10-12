/**
 * Example written by Bruno Lowagie
 */
package sandbox.pdfa;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class HelloPdfA2a {
    
    public static final String DEST = "results/pdfa/hello_pdf_a2a.pdf";
    public static final String FONT = "resources/fonts/OpenSans-Regular.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HelloPdfA2a().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Font font = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Document document = new Document();
        PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(dest), PdfAConformanceLevel.PDF_A_2A);
        writer.createXmpMetadata();
        writer.setTagged();
        document.open();
        document.addLanguage("en-us");
        File file = new File("resources/data/sRGB_CS_profile.icm");
        ICC_Profile icc = ICC_Profile
                .getInstance(new FileInputStream(file));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
        Paragraph p = new Paragraph("Hello World!", font);
        document.add(p);
        document.close();
    }
}