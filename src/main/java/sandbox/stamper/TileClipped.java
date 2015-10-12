/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/32769493/tiling-with-itext-and-adding-margins
 */
package sandbox.stamper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class TileClipped {

    public static final String SRC = "resources/pdfs/hero.pdf";
    public static final String DEST = "results/stamper/hero_tiled_clipped.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TileClipped().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
        float margin = 30;
    	// Creating a reader
        PdfReader reader = new PdfReader(src);
        Rectangle rect = reader.getPageSizeWithRotation(1);
        Rectangle pagesize = new Rectangle(rect.getWidth() + margin * 2, rect.getHeight() + margin * 2);
        // step 1
        Document document = new Document(pagesize);
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte content = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        // adding the same page 16 times with a different offset
        float x, y;
        for (int i = 0; i < 16; i++) {
            x = -rect.getWidth() * (i % 4) + margin;
            y = rect.getHeight() * (i / 4 - 3) + margin;
            content.rectangle(margin, margin, rect.getWidth(), rect.getHeight());
            content.clip();
            content.newPath();
            content.addTemplate(page, 4, 0, 0, 4, x, y);
            document.newPage();
        }
        // step 4
        document.close();
        reader.close();
    }
}
