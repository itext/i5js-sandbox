/*
 * This example was written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/30369587/how-to-write-bulleted-list-in-pdf-using-itext-jar-but-item-should-not-in-next-li
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class Bullets {
    
    public static final String DEST = "results/objects/bullets.pdf";
    public static final String[] ITEMS = {
        "Insurance system", "Agent", "Agency", "Agent Enrollment", "Agent Settings",
        "Appointment", "Continuing Education", "Hierarchy", "Recruiting", "Contract",
        "Message", "Correspondence", "Licensing", "Party"
    };

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Bullets().createPdf(DEST);
    }
    
    
    public void createPdf(String dest) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 8);
        Font font = new Font();
        Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);
        
        Paragraph p = new Paragraph("Items can be split if they don't fit at the end: ", font);
        for (String item: ITEMS) {
            p.add(bullet);
            p.add(new Phrase(" " + item + " ", font));
        }
        document.add(p);
        document.add(Chunk.NEWLINE);
        
        p = new Paragraph("Items can't be split if they don't fit at the end: ", font);
        for (String item: ITEMS) {
            p.add(bullet);
            p.add(new Phrase("\u00a0" + item.replace(' ', '\u00a0') + " ", font));
        }
        document.add(p);
        document.add(Chunk.NEWLINE);
        
        BaseFont bf = BaseFont.createFont("resources/fonts/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 12);
        p = new Paragraph("Items can't be split if they don't fit at the end: ", f);
        for (String item: ITEMS) {
            p.add(new Phrase("\u2022\u00a0" + item.replace(' ', '\u00a0') + " ", f));
        }
        document.add(p);
        
        document.close();
    }
}
