/**
 * Example written by Bruno Lowagie and Nishanthi Grashia in answer to the following question:
 * http://stackoverflow.com/questions/33440294/create-table-in-itext-pdf-in-java
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class RightCornerTable {
    public static final String DEST = "results/tables/right_corner_table.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RightCornerTable().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Rectangle pagesize = new Rectangle(300, 300);
        Document document = new Document(pagesize, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(30);
        Font white = new Font();
        white.setColor(BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(" Date" , white));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setBorderColor(BaseColor.GRAY);
        cell.setBorderWidth(2f);
        table.addCell(cell);
        PdfPCell cellTwo = new PdfPCell(new Phrase("10/01/2015"));
        cellTwo.setBorderWidth(2f);
        table.addCell(cellTwo);
        document.add(table);
        document.newPage();
        table.setTotalWidth(90);
        PdfContentByte canvas = writer.getDirectContent();
        table.writeSelectedRows(0, -1, document.right() - 90, document.top(), canvas);
        document.close();
    }

}
