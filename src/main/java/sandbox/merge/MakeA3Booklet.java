/*
 * Example written by Bruno Lowagie in answer to a question on StackOverflow:
 * http://stackoverflow.com/questions/34019298
 */
package sandbox.merge;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
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
public class MakeA3Booklet {

    /** The original PDF file. */
    public static final String SRC = "resources/pdfs/primes.pdf";

    /** The resulting PDF file. */
    public static final String DEST = "results/merge/a3_booklet.pdf";
    
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
        new MakeA3Booklet().manipulatePdf(SRC, DEST);
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
        Document document = new Document(PageSize.A3.rotate());
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte canvas = writer.getDirectContent();
        float a4_width = PageSize.A4.getWidth();
        int n = reader.getNumberOfPages();
        int p = 0;
        PdfImportedPage page;
        while (p++ < n) {
            page = writer.getImportedPage(reader, p);
            if (p % 2 == 1) {
                canvas.addTemplate(page, 0, 0);
            }
            else {
                canvas.addTemplate(page, a4_width, 0);
                document.newPage();
            }
        }
        // step 5
        document.close();
        reader.close();
    }
    
}
