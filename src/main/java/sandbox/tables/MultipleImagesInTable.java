/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/34303448
 */
package sandbox.tables;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
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
public class MultipleImagesInTable {
    
    public static final String DEST = "results/tables/images_in_table_sequence.pdf";
    public static final String IMG1 = "resources/images/brasil.png";
    public static final String IMG2 = "resources/images/dog.bmp";
    public static final String IMG3 = "resources/images/fox.bmp";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultipleImagesInTable().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Image img1 = Image.getInstance(IMG1);
        Image img2 = Image.getInstance(IMG2);
        Image img3 = Image.getInstance(IMG3);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(20);
        table.addCell(img1);
        table.addCell("Brazil");
        table.addCell(img2);
        table.addCell("Dog");
        table.addCell(img3);
        table.addCell("Fox");
        document.add(table);
        document.close();
    }
}
