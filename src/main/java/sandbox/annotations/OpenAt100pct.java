/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/31314993/how-to-set-a-default-zoom-of-100-using-itext-java
 */
package sandbox.annotations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class OpenAt100pct {
    
    public static final String DEST = "results/annotations/open100pct.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new OpenAt100pct().createPdf(DEST);
    }
    public void createPdf(String dest) throws FileNotFoundException, DocumentException {
        Rectangle pagesize = PageSize.LETTER;
        Document document = new Document(pagesize);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfDestination pdfDest = new PdfDestination(PdfDestination.XYZ, 0, 842, 1f);
        writer.setOpenAction(PdfAction.gotoLocalPage(1, pdfDest, writer));
        document.add(new Paragraph("Hello World"));
        document.close();
    }
}
