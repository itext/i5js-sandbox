/**
 * Example written by Bruno Lowagie.
 * http://stackoverflow.com/questions/29563942/how-to-add-a-cover-pdf-in-a-existing-itext-document
 */
package sandbox.merge;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddCover2 {

    /** The original PDF file. */
    public static final String COVER
        = "resources/pdfs/hero.pdf";
    /** The original PDF file. */
    public static final String SRC
        = "resources/pdfs/pages.pdf";

    /** The resulting PDF file. */
    public static final String DEST
        = "results/merge/cover_with_pages.pdf";
    
    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
        PdfReader cover = new PdfReader(COVER);
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.insertPage(1, cover.getPageSizeWithRotation(1));
        PdfContentByte page1 = stamper.getOverContent(1);
        PdfImportedPage page = stamper.getImportedPage(cover, 1);
        page1.addTemplate(page, 0, 0);
        stamper.close();
        cover.close();
        reader.close();
    }

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
        new AddCover2().manipulatePdf(SRC, DEST);
    }
}

