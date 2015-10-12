/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/32839816/export-hyperlink-to-pdf-file-from-itextsharp-library-in-c-sharp
 */
package sandbox.annotations;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Annotation;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class ImagesLinksTable {
    public static final String DEST = "results/annotations/images_links_table.pdf";
    public static final String IMG = "resources/images/info.png";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImagesLinksTable().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(3);
        Image img = Image.getInstance(IMG);
        Chunk c = new Chunk(img, 0, 0, true);
        Anchor anchor = new Anchor(c);
        anchor.setReference("http://lowagie.com/");
        table.addCell(anchor);
        table.addCell("A");
        table.addCell("B");
        table.addCell("C");
        Chunk c2 = new Chunk(img, 0, 0, true);
        c2.setAction(new PdfAction("http://itextpdf.com"));
        table.addCell(new Phrase(c2));
        table.addCell("D");
        table.addCell("E");
        table.addCell("F");
        Image img2 = Image.getInstance(IMG);
        Annotation a = new Annotation(0, 0, 0, 0, "http://lowagie.com/bio");
        img2.setAnnotation(a);
        table.addCell(img2);
        document.add(table);
        document.close();
    }
}
