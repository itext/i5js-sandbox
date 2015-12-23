/*
 * Example written by Bruno Lowagie in answer to a question on StackOverflow:
 * http://stackoverflow.com/questions/34432502
 */
package sandbox.merge;

import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
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
public class MakeBooklet {

    /** The original PDF file. */
    public static final String SRC = "resources/pdfs/primes.pdf";

    /** The resulting PDF file. */
    public static final String DEST = "results/merge/book16.pdf";
    
    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MakeBooklet().manipulatePdf(SRC, DEST);
    }
    
    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
    	// Creating a reader
        PdfReader reader = new PdfReader(src);
        // step 1
        Rectangle pagesize = new Rectangle(
            PageSize.A4.getWidth() * 4,
            PageSize.A4.getHeight() * 2);
        Document document = new Document(pagesize);
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte canvas = writer.getDirectContent();
        float a4_width = PageSize.A4.getWidth();
        float a4_height = PageSize.A4.getHeight();
        int n = reader.getNumberOfPages();
        int p = 1;
        PdfImportedPage page;
        while ((p - 1) / 16 <= n / 16) {
            addPage(canvas, reader, p + 3, 0);
            addPage(canvas, reader, p + 12, a4_width);
            addPage(canvas, reader, p + 15, a4_width * 2);
            addPage(canvas, reader, p, a4_width * 3);
            AffineTransform at = AffineTransform.getRotateInstance(-Math.PI);
            at.concatenate(AffineTransform.getTranslateInstance(0, -a4_height * 2));
            canvas.saveState();
            canvas.concatCTM(at);
            addPage(canvas, reader, p + 4, -a4_width);
            addPage(canvas, reader, p + 11, -a4_width * 2);
            addPage(canvas, reader, p + 8, -a4_width * 3);
            addPage(canvas, reader, p + 7, -a4_width * 4);
            canvas.restoreState();
            document.newPage();
            addPage(canvas, reader, p + 1, 0);
            addPage(canvas, reader, p + 14, a4_width);
            addPage(canvas, reader, p + 13, a4_width * 2);
            addPage(canvas, reader, p + 2, a4_width * 3);
            canvas.saveState();
            canvas.concatCTM(at);
            addPage(canvas, reader, p + 6, -a4_width);
            addPage(canvas, reader, p + 9, -a4_width * 2);
            addPage(canvas, reader, p + 10, -a4_width * 3);
            addPage(canvas, reader, p + 5, -a4_width * 4);
            canvas.restoreState();
            document.newPage();
            p += 16;
        }
        // step 5
        document.close();
        reader.close();
    }
    
    public void addPage(PdfContentByte canvas,
            PdfReader reader, int p, float x) {
        if (p > reader.getNumberOfPages()) return;
        PdfImportedPage page = canvas.getPdfWriter().getImportedPage(reader, p);
        canvas.addTemplate(page, x, 0);
    }
}
