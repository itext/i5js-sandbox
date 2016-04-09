/**
 * This example was written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/36511649
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class WatermarkToTheSide {

    public static final String SRC = "resources/pdfs/pages.pdf";
    public static final String DEST = "results/stamper/side_watermark.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new WatermarkToTheSide().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        int n = reader.getNumberOfPages();
        PdfContentByte canvas;
        Rectangle pageSize;
        float x, y;
        for (int p = 1; p <= n; p++) {
            pageSize = reader.getPageSizeWithRotation(p);
            // left of the page
            x = pageSize.getLeft();
            // middle of the height
            y = (pageSize.getTop() + pageSize.getBottom()) / 2;
            // getting the canvas covering the existing content
            canvas = stamper.getOverContent(p);
            // adding some lines to the left
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("This is some extra text added to the left of the page"),
                x + 18, y, 90);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("This is some more text added to the left of the page"),
                x + 34, y, 90);
        }
        stamper.close();
    }
    
}
