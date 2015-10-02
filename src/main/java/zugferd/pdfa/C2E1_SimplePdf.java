/*
 * This code sample was written in the context of the tutorial:
 * ZUGFeRD: The future of Invoicing
 */
package zugferd.pdfa;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

/**
 * Creates a simple PDF with images and text.
 */
@WrapToTest
public class C2E1_SimplePdf {

    /** The resulting PDF. */
    public static final String DEST = "results/zugferd/pdfa/quickbrownfox1.pdf";
    /** An image resource. */
    public static final String FOX = "resources/images/fox.bmp";
    /** An image resource. */
    public static final String DOG = "resources/images/dog.bmp";

    /**
     * Creates a simple PDF with images and text.
     * @param args no arguments needed.
     * @throws IOException
     * @throws DocumentException 
     */
    static public void main(String args[]) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C2E1_SimplePdf().createPdf(DEST);
    }
    
    /**
     * Creates a simple PDF with images and text
     * @param dest  the resulting PDF
     * @throws IOException
     * @throws DocumentException 
     */
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        document.open();
        Paragraph p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.HELVETICA, 20));
        Chunk c = new Chunk("The quick brown ");
        p.add(c);
        Image i = Image.getInstance(FOX);
        c = new Chunk(i, 0, -24);
        p.add(c);
        c = new Chunk(" jumps over the lazy ");
        p.add(c);
        i = Image.getInstance(DOG);
        c = new Chunk(i, 0, -24);
        p.add(c);
        document.add(p);
        document.close();
    }

}
