/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/33633363/itextpdf-cannot-use-writeselectedrows-on-a-table-where-an-anchor-has-been-in
 */
package sandbox.tables;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class LinkInPositionedTable {
    
    public static final String DEST = "results/tables/link_in_positioned_table.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new LinkInPositionedTable().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(500);
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();  
        Anchor anchor = new Anchor("link to top of next page");
        anchor.setReference("#top");
        p.add(anchor);
        cell.addElement(p);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 36, 700, writer.getDirectContent());
        document.newPage();
        Anchor target = new Anchor("top");
        target.setName("top");
        document.add(target);
        document.close();
    }
}
