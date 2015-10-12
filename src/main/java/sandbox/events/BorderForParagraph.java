/*
 * This question was answered by Bruno Lowagie in answer to the following question on StackOverflow:
 * http://stackoverflow.com/questions/30053684/how-to-add-border-to-paragraph-in-itext-pdf-library-in-java
 */
package sandbox.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class BorderForParagraph {
    public static final String DEST = "results/events/paragraph_with_border.pdf";
    
    class ParagraphBorder extends PdfPageEventHelper {
        public boolean active = false;
        public void setActive(boolean active) {
            this.active = active;
        }

        public float offset = 5;
        public float startPosition;

        @Override
        public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
            this.startPosition = paragraphPosition;
        }
        
        @Override
        public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
            if (active) {
                PdfContentByte cb = writer.getDirectContentUnder();
                cb.rectangle(document.left(), paragraphPosition - offset,
                    document.right() - document.left(), startPosition - paragraphPosition);
                cb.stroke();
            }
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BorderForParagraph().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        ParagraphBorder border = new ParagraphBorder();
        writer.setPageEvent(border);
        document.open();
        document.add(new Paragraph("Hello,"));
        document.add(new Paragraph("In this document, we'll add several paragraphs that will trigger page events. As long as the event isn't activated, nothing special happens, but let's make the event active and see what happens:"));
        border.setActive(true);
        document.add(new Paragraph("This paragraph now has a border. Isn't that fantastic? By changing the event, we can even provide a background color, change the line width of the border and many other things. Now let's deactivate the event."));
        border.setActive(false);
        document.add(new Paragraph("This paragraph no longer has a border."));
        document.close();
    }
}
