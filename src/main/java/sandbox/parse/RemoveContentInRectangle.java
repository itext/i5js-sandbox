/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27905740/remove-text-occurrences-contained-in-a-specified-area-with-itext
 */

package sandbox.parse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sandbox.WrapToTest;

@WrapToTest
public class RemoveContentInRectangle {

    public static final String SRC = "resources/pdfs/page229.pdf";
    public static final String DEST = "results/parse/page229_removed_content.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RemoveContentInRectangle().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<PdfCleanUpLocation>();
        cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(97f, 405f, 480f, 445f), BaseColor.GRAY));
        PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
        cleaner.cleanUp();
        stamper.close();
        reader.close();
    }
}
