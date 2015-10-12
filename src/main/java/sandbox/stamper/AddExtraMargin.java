/**
 * This example was written by Bruno Lowagie in answer to the following StackOverflow question:
 * http://stackoverflow.com/questions/29775893/watermark-in-a-pdf-with-itext
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddExtraMargin {

    public static final String SRC = "resources/pdfs/primes.pdf";
    public static final String DEST = "results/stamper/primes_extra_margin.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddExtraMargin().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        int n = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        // properties
        PdfContentByte over;
        PdfDictionary pageDict;
        PdfArray mediabox;
        float llx, lly, ury;
        // loop over every page
        for (int i = 1; i <= n; i++) {
            pageDict = reader.getPageN(i);
            mediabox = pageDict.getAsArray(PdfName.MEDIABOX);
            llx = mediabox.getAsNumber(0).floatValue();
            lly = mediabox.getAsNumber(1).floatValue();
            ury = mediabox.getAsNumber(3).floatValue();
            mediabox.set(0, new PdfNumber(llx - 36));
            over = stamper.getOverContent(i);
            over.saveState();
            over.setColorFill(new GrayColor(0.5f));
            over.rectangle(llx - 36, lly, 36, ury - llx);
            over.fill();
            over.restoreState();
        }
        stamper.close();
        reader.close();
    }
}
