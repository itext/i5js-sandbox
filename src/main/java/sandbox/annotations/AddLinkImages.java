/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/29388313/itext-how-to-associate-actions-with-graphical-object
 */
package sandbox.annotations;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class AddLinkImages {
    
    public static final String DEST = "results/annotations/link_images.pdf";
    
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddLinkImages().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        document.add(new Paragraph("Objects with links"));
        Paragraph p = new Paragraph();
        p.add(createImage("resources/images/info.png", "http://itextpdf.com/"));
        p.add(createImage("resources/images/dog.bmp", "http://pages.itextpdf.com/ebook-stackoverflow-questions.html"));
        p.add(createImage("resources/images/fox.bmp", "http://stackoverflow.com/q/29388313/1622493"));
        p.add(createImage("resources/images/butterfly.wmf", "http://stackoverflow.com/questions/tagged/itext*"));
        document.add(p);
        document.close();
    }
    
    public Chunk createImage(String src, String url) throws BadElementException, IOException {
        Image img = Image.getInstance(src);
        Chunk chunk = new Chunk(img, 0, 0, true);
        chunk.setAction(new PdfAction(url));
        return chunk;
    }
}
