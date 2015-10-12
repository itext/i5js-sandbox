/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/32130219/itext-list-of-images-in-a-cell
 */
package sandbox.tables;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie
 */
@WrapToTest
public class ImagesInChunkInCell {
    public static final String IMG = "resources/images/bulb.gif";
    public static final String DEST = "results/tables/list_with_images.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImagesInChunkInCell().createPdf(DEST);
    }
        
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Image image = Image.getInstance(IMG);
        image.setScaleToFitHeight(false);
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(new float[]{120});
        table.setLockedWidth(true);
        Phrase listOfDots = new Phrase();
        for (int i = 0; i < 40; i++) {
            listOfDots.add(new Chunk(image, 0, 0));
            listOfDots.add(new Chunk(" "));
        }
        table.addCell(listOfDots);
        document.add(table);
        document.close();
    }
}
