/*
 * Example written by Bruno Lowagie in answer to a question on StackOverflow:
 * http://stackoverflow.com/questions/34432502
 */
package sandbox.merge;

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
public class MakeBookletA3 {

    /** The original PDF file. */
    public static final String SRC = "resources/pdfs/primes.pdf";

    /** The resulting PDF file. */
    public static final String DEST = "results/merge/book4.pdf";
    
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
        new MakeBookletA3().manipulatePdf(SRC, DEST);
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
            PageSize.A4.getWidth() * 2,
            PageSize.A4.getHeight());
        Document document = new Document(pagesize);
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte canvas = writer.getDirectContent();
        float a4_width = PageSize.A4.getWidth();
        int n = reader.getNumberOfPages();
        int p = 1;
        PdfImportedPage page;
        while ((p - 1) / 4 <= n / 4) {
            addPage(canvas, reader, p + 3, 0);
            addPage(canvas, reader, p, a4_width);
            document.newPage();
            addPage(canvas, reader, p + 1, 0);
            addPage(canvas, reader, p + 2, a4_width);
            document.newPage();
            p += 4;
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
