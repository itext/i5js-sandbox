/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28502315/divide-page-in-2-parts-so-we-can-fill-each-with-different-source
 */
package sandbox.events;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import sandbox.WrapToTest;

@WrapToTest
public class ThreeParts {
    public static final String DEST = "results/events/three_parts.pdf";
    
    public static final String[] LANGUAGES = { "la", "en", "fr" };
    public static final Rectangle[] RECTANGLES = {
        new Rectangle(36, 581, 559, 806),
        new Rectangle(36, 308.5f, 559, 533.5f),
        new Rectangle(36, 36, 559, 261) };
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ThreeParts().createPdf(DEST);
    }
    
    public class RedBorder extends PdfPageEventHelper {
        protected Rectangle[] rectangles;
        
        public RedBorder() {
            rectangles = new Rectangle[3];
            for (int i = 0; i < 3; i++) {
                rectangles[i] = new Rectangle(RECTANGLES[i]);
                rectangles[i].setBorder(Rectangle.BOX);
                rectangles[i].setBorderWidth(1);
                rectangles[i].setBorderColor(BaseColor.RED);
            }
        }
        
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContent();
            for (Rectangle rectangle : rectangles) {
                canvas.rectangle(rectangle);
            }
        }
    }
    
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        RedBorder event = new RedBorder();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        ColumnText[] columns = new ColumnText[3];
        for (int section = 1; section <= 3; section++) {
            for (int la = 0; la < 3; la++) {
                columns[la] = createColumn(cb, section, LANGUAGES[la], RECTANGLES[la]);
            }
            while (addColumns(columns)) {
                document.newPage();
                for (int la = 0; la < 3; la++) {
                    columns[la].setSimpleColumn(RECTANGLES[la]);
                }
            }
            document.newPage();
        }
        // step 5
        document.close();
    }
    
    public ColumnText createColumn(PdfContentByte cb, int i, String la, Rectangle rect) throws IOException {
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(rect);
        Phrase p = createPhrase(String.format("resources/text/liber1_%s_%s.txt", i, la));
        ct.addText(p);
        return ct;
    }
    
    public Phrase createPhrase(String path) throws IOException {
        Phrase p = new Phrase();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream(path), "UTF8"));
        String str;
        while ((str = in.readLine()) != null) {
            p.add(str);
        }
        in.close();
        return p;
    }
    
    public boolean addColumns(ColumnText[] columns) throws DocumentException {
        int status = ColumnText.NO_MORE_TEXT;
        for (ColumnText column : columns) {
            if (ColumnText.hasMoreText(column.go()))
                status = ColumnText.NO_MORE_COLUMN;
        }
        return ColumnText.hasMoreText(status);
    }
}
