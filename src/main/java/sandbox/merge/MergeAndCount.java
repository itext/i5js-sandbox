/**
 * Example written by Bruno Lowagie.
 * This example will only work with iText 5.5.6 and higher (you also need the xtra package).
 */
package sandbox.merge;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.util.SmartPdfSplitter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

public class MergeAndCount {

    /** The original PDF file. */
    public static final String SRC
        = "resources/pdfs/Wrong.pdf";

    /** The resulting PDF file. */
    public static final String DEST
        = "results/merge/pages_counted.pdf";
    
    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        SmartPdfSplitter splitter = new SmartPdfSplitter(reader);
        int part = 1;
        while (splitter.hasMorePages()) {
            splitter.split(new FileOutputStream("results/merge/part_" + part + ".pdf"), 200000);
            part++;
        }
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
        LoggerFactory.getInstance().setLogger(new SysoLogger());
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MergeAndCount().manipulatePdf(SRC, DEST);
    }
}

