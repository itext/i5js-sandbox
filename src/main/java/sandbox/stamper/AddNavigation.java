/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/31240828/how-navigate-one-page-to-another-page-in-pdf-file-by-using-itextsharp-c
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class AddNavigation {
    
    public static final String SRC = "resources/pdfs/primes.pdf";
    public static final String DEST = "results/stamper/primes_links.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddNavigation().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfDestination d1 = new PdfDestination(PdfDestination.FIT);
        Rectangle rect = new Rectangle(0, 806, 595, 842);
        PdfAnnotation a10 = PdfAnnotation.createLink(stamper.getWriter(), rect, PdfAnnotation.HIGHLIGHT_INVERT, 10, d1);
        stamper.addAnnotation(a10, 1);
        PdfDestination d2 = new PdfDestination(PdfDestination.FIT);
        PdfAnnotation a1 = PdfAnnotation.createLink(stamper.getWriter(), rect, PdfAnnotation.HIGHLIGHT_PUSH, 1, d2);
        stamper.addAnnotation(a1, 10);
        stamper.close();
    }
}
