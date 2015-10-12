/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/28332692/itextsharp-rotated-pdf-page-reverts-orientation-when-file-is-rasterized-at-print
 */
package sandbox.merge;

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
 * This example is named IncorrectExample because this is not how you would
 * typically solve the problem of rotating pages, nor of merging documents.
 * The correct way is NOT to use Document / PdfWriter, but to use PdfStamper,
 * PdfCopy or PdfSmartCopy. However: in the question mentioned above, the
 * circumstances are very particular and using this example in those
 * circumstances is justified.
 */
@WrapToTest
public class IncorrectExample {

    /** The original PDF file. */
    public static final String SRC
        = "resources/pdfs/pages.pdf";

    /** The resulting PDF file. */
    public static final String DEST
        = "results/merge/pages_changed.pdf";
    
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
        Rectangle pagesize = getPageSize(reader, 1);
        Document document = new Document(pagesize);
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            pagesize = getPageSize(reader, i);
            document.setPageSize(pagesize);
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(reader, i);
            if (isPortrait(reader, i)) {
                cb.addTemplate(page, 0, 0);
            }
            else {
                cb.addTemplate(page, 0, 1, -1, 0, pagesize.getWidth(), 0);
            }
        }
        // step 4
        document.close();
        reader.close();
    }

    public Rectangle getPageSize(PdfReader reader, int pagenumber) {
        Rectangle pagesize = reader.getPageSizeWithRotation(pagenumber);
        return new Rectangle(
                Math.min(pagesize.getWidth(), pagesize.getHeight()),
                Math.max(pagesize.getWidth(), pagesize.getHeight()));
    }

    public boolean isPortrait(PdfReader reader, int pagenumber) {
        Rectangle pagesize = reader.getPageSize(pagenumber);
        return pagesize.getHeight() > pagesize.getWidth();
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
        new IncorrectExample().manipulatePdf(SRC, DEST);
    }
}

