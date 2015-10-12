/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30843163/how-to-add-inline-images-in-itext-when-using-columntext-in-text-mode
 */
package sandbox.columntext;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
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
public class ColumnTextChunkImage {
    public static final String DOG = "resources/images/dog.bmp";
    public static final String FOX = "resources/images/fox.bmp";
    public static final String DEST = "results/columntext/column_text_chunk_image.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColumnTextChunkImage().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        Image dog = Image.getInstance(DOG);
        Image fox = Image.getInstance(FOX);
        Phrase p = new Phrase("quick brown fox jumps over the lazy dog.");
        p.add("Or, to say it in a more colorful way: quick brown ");
        p.add(new Chunk(fox, 0, 0, true));
        p.add(" jumps over the lazy ");
        p.add(new Chunk(dog, 0, 0, true));
        p.add(".");
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(new Rectangle(50, 600, 400, 800));
        ct.addText(p);
        ct.go();
        // step 5
        document.close();
    }
}
