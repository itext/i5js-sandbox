/**
 * This example is written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/32527211/itext-how-to-check-if-giant-string-is-present-on-the-pdf-page
 */
package sandbox.logging;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author iText
 */
@WrapToTest
public class CustomPageDictKeyCreate {
    

    public static final String DEST = "results/logging/custom_page_dict_key.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        CustomPageDictKeyCreate app = new CustomPageDictKeyCreate();
        app.createPdf(DEST);
        app.check(DEST);
    }
    
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Page 1"));
        document.newPage();
        document.add(new Paragraph("Page 2"));
        document.newPage();
        document.add(new Paragraph("Page 3"));
        document.newPage();
        document.add(new Paragraph("Page 4"));
        writer.addPageDictEntry(new PdfName("ITXT_PageMarker"), new PdfString("Marker for page 4"));
        document.newPage();
        document.add(new Paragraph("Page 5"));
        document.newPage();
        document.add(new Paragraph("Page 6"));
        writer.addPageDictEntry(new PdfName("ITXT_PageMarker"), new PdfName("PageMarker"));
        document.newPage();
        document.add(new Paragraph("Page 7"));
        writer.addPageDictEntry(new PdfName("ITXT_PageMarker"), new PdfNumber(7));
        document.newPage();
        document.add(new Paragraph("Page 8"));
        document.close();
    }
    
    public void check(String filename) throws IOException {
        PdfReader reader = new PdfReader(filename);
        PdfDictionary pagedict;
        for (int i = 1; i < reader.getNumberOfPages(); i++) {
            pagedict = reader.getPageN(i);
            System.out.println(pagedict.get(new PdfName("ITXT_PageMarker")));
        }
        reader.close();
    }
}
