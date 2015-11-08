/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29152313/fix-the-orientation-of-a-pdf-in-order-to-scale-it
 */
package sandbox.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class Seascape {
    public static final String DEST = "results/events/seascape.pdf";
    
    public class Rotate extends PdfPageEventHelper {
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, PdfPage.SEASCAPE);
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Seascape().createPdf(DEST);
    }
    
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Rotate event = new Rotate();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        for (int i = 0; i < 50; i++)
            document.add(new Paragraph("Hello World!"));
        document.newPage();
        document.add(new Paragraph("Hello World!"));
        document.newPage();
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();
    }
}
