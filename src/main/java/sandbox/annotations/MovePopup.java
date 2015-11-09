/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/33609564/how-to-modify-size-for-pdfannotations-in-itext-and-disable-some-options
 */
package sandbox.annotations;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfRectangle;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class MovePopup {
    
    public static final String SRC = "resources/pdfs/hello_sticky_note.pdf";
    public static final String DEST = "results/annotations/hello_sticky_moved.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MovePopup().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary page = reader.getPageN(1);
        PdfArray annots = page.getAsArray(PdfName.ANNOTS);
        PdfDictionary sticky = annots.getAsDict(0);
        PdfArray stickyRect = sticky.getAsArray(PdfName.RECT);
        PdfRectangle stickyRectangle = new PdfRectangle(
            stickyRect.getAsNumber(0).floatValue() - 120, stickyRect.getAsNumber(1).floatValue() -70,
            stickyRect.getAsNumber(2).floatValue(), stickyRect.getAsNumber(3).floatValue() - 30
        );
        sticky.put(PdfName.RECT, stickyRectangle);
        PdfDictionary popup = annots.getAsDict(1);
        PdfArray popupRect = popup.getAsArray(PdfName.RECT);
        PdfRectangle popupRectangle = new PdfRectangle(
            popupRect.getAsNumber(0).floatValue() - 250, popupRect.getAsNumber(1).floatValue(),
            popupRect.getAsNumber(2).floatValue(), popupRect.getAsNumber(3).floatValue() - 250
        );
        popup.put(PdfName.RECT, popupRectangle);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
    }
}
