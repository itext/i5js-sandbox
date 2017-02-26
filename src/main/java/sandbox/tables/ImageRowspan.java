/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/42440133/itext-cell-with-image-doesnt-apply-rowspan
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ImageRowspan {
    public static final String DEST = "results/tables/image_rowspan.pdf";
    public static final String IMG = "resources/images/bruno.jpg";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImageRowspan().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        PdfPCell imageCell = new PdfPCell();
        imageCell.addElement(Image.getInstance(IMG));
        imageCell.setRowspan(2);
        table.addCell(imageCell);
        table.addCell(new Phrase("1"));
        table.addCell(new Phrase("2"));
        document.add(table);
        document.close();
    }
}