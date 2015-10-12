/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29092738/itext-chapter-title-and-columntext
 */
package sandbox.events;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sandbox.WrapToTest;

@WrapToTest
public class CreateTOCinColumn {
    
    public static final String DEST = "results/events/columns_with_toc.pdf";
    public static final Rectangle[] COLUMNS = {
        new Rectangle(36, 36, 192, 806),
        new Rectangle(204, 36, 348, 806),
        new Rectangle(360, 36, 504, 806)
    };
    
    public class TOCEntry {
        protected PdfAction action;
        protected String title;
    }
    
    public class TOCCreation extends PdfPageEventHelper {

        protected PdfOutline root;
        protected List<TOCEntry> toc = new ArrayList<TOCEntry>();
        
        public TOCCreation() {
        }
        
        public void setRoot(PdfOutline root) {
            this.root = root;
        }
        
        public List<TOCEntry> getToc() {
            return toc;
        }
        
        @Override
        public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
            PdfDestination dest = new PdfDestination(PdfDestination.XYZ, rect.getLeft(), rect.getTop(), 0);
            new PdfOutline(root, dest, text);
            TOCEntry entry = new TOCEntry();
            entry.action = PdfAction.gotoLocalPage(writer.getPageNumber(), dest, writer);
            entry.title = text;
            toc.add(entry);
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CreateTOCinColumn().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        TOCCreation event = new TOCCreation();
        writer.setPageEvent(event);
        document.open();
        event.setRoot(writer.getRootOutline());
        ColumnText ct = new ColumnText(writer.getDirectContent());
        int start;
        int end;
        for (int i = 0; i <= 20; ) {
            start = (i * 10) + 1;
            i++;
            end = i * 10;
            String title = String.format("Numbers from %s to %s", start, end);
            Chunk c = new Chunk(title);
            c.setGenericTag(title);
            ct.addElement(c);
            ct.addElement(createTable(start, end));
        }
        int column = 0;
        do {
            if (column == 3) {
                document.newPage();
                column = 0;
            }
            ct.setSimpleColumn(COLUMNS[column++]);
        } while (ColumnText.hasMoreText(ct.go()));
        document.newPage();
        for (TOCEntry entry : event.getToc()) {
            Chunk c = new Chunk(entry.title);
            c.setAction(entry.action);
            document.add(new Paragraph(c));
        }
        document.close();
    }
    
    public PdfPTable createTable(int start, int end) throws IOException {
        PdfPTable table = new PdfPTable(2);
        for (int i = start; i <= end; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("Test");
        }
        return table;
    }
}
