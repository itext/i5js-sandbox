/*
 * This example is written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/39037594
 */
package sandbox.events;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class CreateTOC {
    
    public static final String DEST = "results/events/create_toc.pdf";
    public final Font titleFont = new Font(FontFamily.HELVETICA, 16);
    
    public class TOCEvent extends PdfPageEventHelper {

        protected List<SimpleEntry<String, Integer>> toc = new ArrayList<>();
        
        @Override
        public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
            toc.add(new SimpleEntry(text, writer.getPageNumber()));
        }
        
        public List getTOC() {
            return toc;
        }
    }
    
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CreateTOC().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        TOCEvent event = new TOCEvent();
        writer.setPageEvent(event);
        document.open();
        for (int i = 0; i < 10; i++) {
            String title = "This is title " + i;
            Chunk c = new Chunk(title, titleFont);
            c.setGenericTag(title);
            document.add(new Paragraph(c));
            for (int j = 0; j < 50; j++) {
                document.add(new Paragraph("Line " + j + " of title " + i));
            }
        }
        document.newPage();
        document.add(new Paragraph("Table of Contents", titleFont));
        Chunk dottedLine = new Chunk(new DottedLineSeparator());
        List<SimpleEntry<String, Integer>> entries = event.getTOC();
        Paragraph p;
        for (SimpleEntry<String, Integer> entry : entries) {
            p = new Paragraph(entry.getKey());
            p.add(dottedLine);
            p.add(String.valueOf(entry.getValue()));
            document.add(p);
        }
        document.close();
    }
}
