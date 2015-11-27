/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/33873180/itext-pdf-add-text-in-absolute-position-on-top-of-the-1st-page
 */
package sandbox.columntext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author iText
 */
public class WriteOnFirstPage {
    public static final String DEST = "results/columntext/write_on_first_page.pdf";
    
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new WriteOnFirstPage().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        PdfTemplate message = cb.createTemplate(523, 50);
        Image header = Image.getInstance(message);
        document.add(header);
        for (int i = 0; i < 100; i++) {
            document.add(new Paragraph("test"));
        }
        ColumnText ct = new ColumnText(message);
        ct.setSimpleColumn(new Rectangle(0, 0, 523, 50));
        ct.addElement(
                new Paragraph(
                        String.format("There are %s pages in this document", writer.getPageNumber())));
        ct.go();
        // step 5
        document.close();
    }
    
}
