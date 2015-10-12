/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/24037282/any-way-to-create-redactions
 */

package sandbox.parse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class RemoveRedactedContent {

    public static final String SRC = "resources/pdfs/page229_redacted.pdf";
    public static final String DEST = "results/parse/page229_apply_redacted.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RemoveRedactedContent().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(stamper);
        cleaner.cleanUp();
        stamper.close();
        reader.close();
    }
}
