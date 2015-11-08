/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/33582245/extract-pdf-page-and-insert-into-existing-pdf
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class SuperImpose {
    public static final String SRC = "resources/pdfs/primes.pdf";
    public static final String[] EXTRA =
        {"resources/pdfs/hello.pdf", "resources/pdfs/base_url.pdf", "resources/pdfs/state.pdf"};
    public static final String DEST = "results/stamper/primes_superimpose.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SuperImpose().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte canvas = stamper.getUnderContent(1);
        PdfReader r;
        PdfImportedPage page;
        for (String path : EXTRA) {
            r = new PdfReader(path);
            page = stamper.getImportedPage(r, 1);
            canvas.addTemplate(page, 0, 0);
            stamper.getWriter().freeReader(r);
            r.close();
        }
        stamper.close();
    }
}
