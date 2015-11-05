/**
 * This example is written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/32593183/itextsharp-is-it-possible-to-set-a-different-alignment-in-the-same-cell-for-te
 */
package sandbox.tables;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class TableWithTab {
    public static final String DEST = "results/tables/table_with_tab.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableWithTab().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Chunk glue = new Chunk(new VerticalPositionMark());
        PdfPTable table = new PdfPTable(1);
        Phrase p = new Phrase();
        p.add("Left");
        p.add(glue);
        p.add("Right");
        table.addCell(p);
        document.add(table);
        document.close();
    }
    
}
