/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34480476
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class SimpleTable13 {
    public static final String DEST = "results/tables/simple_table13.pdf";
    public static final String[][] DATA = {
        {"John Edward Jr.", "AAA"},
        {"Pascal Einstein W. Alfi", "BBB"},
        {"St. John", "CCC"}
    };

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable13().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(50);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{5, 1});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.addCell("Name: " + DATA[0][0]);
        table.addCell(DATA[0][1]);
        table.addCell("Surname: " + DATA[1][0]);
        table.addCell(DATA[1][1]);
        table.addCell("School: " + DATA[2][0]);
        table.addCell(DATA[1][1]);
        document.add(table);
        document.close();
    }
}
