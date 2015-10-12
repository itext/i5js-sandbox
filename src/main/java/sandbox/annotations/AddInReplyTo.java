/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/28450668/how-to-add-in-reply-to-annotation-using-itextsharp
 */
package sandbox.annotations;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddInReplyTo {

    public static final String SRC = "resources/pdfs/hello_sticky_note.pdf";
    public static final String DEST = "results/annotations/hello_in_reply_to.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddInReplyTo().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary page = reader.getPageN(1);
        PdfArray annots = page.getAsArray(PdfName.ANNOTS);
        PdfDictionary sticky = annots.getAsDict(0);
        PdfArray stickyRect = sticky.getAsArray(PdfName.RECT);
        PdfDictionary popup = annots.getAsDict(1);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfWriter writer = stamper.getWriter();
        Rectangle stickyRectangle = new Rectangle(
            stickyRect.getAsNumber(0).floatValue(), stickyRect.getAsNumber(1).floatValue(),
            stickyRect.getAsNumber(2).floatValue(), stickyRect.getAsNumber(3).floatValue()
        );
        PdfAnnotation replySticky = PdfAnnotation.createText(
                writer, stickyRectangle, "Reply", "Hello PDF", true, "Comment");
        replySticky.put(PdfName.IRT, annots.getAsIndirectObject(0));
        stamper.addAnnotation(replySticky, 1);
        stamper.close();
    }
}
