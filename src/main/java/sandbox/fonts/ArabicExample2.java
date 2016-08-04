/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/38758449
 */
package sandbox.fonts;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ArabicExample2 {
        
    public static final String DEST = "results/fonts/arabic2_rtl.pdf";
    public static final String FONT = "resources/fonts/NotoNaskhArabic-Regular.ttf";
    
    public static final String ARABIC = "\u0627\u0644\u0633\u0639\u0631 \u0627\u0644\u0627\u062c\u0645\u0627\u0644\u064a";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ArabicExample2().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        PdfPTable table = new PdfPTable(1);
        Phrase phrase = new Phrase();
        Chunk chunk = new Chunk("test value");
        phrase.add(chunk);
        phrase.add(new Chunk(ARABIC, f));
        PdfPCell cell = new PdfPCell(phrase);
        cell.setUseDescender(true);
        cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
