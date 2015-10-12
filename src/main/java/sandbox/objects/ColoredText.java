/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27578497/applying-color-to-strings-in-paragraph-using-itext
 */
package sandbox.objects;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ColoredText {

    public static final String DEST = "results/objects/colored_text.pdf";
    
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColoredText().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font red = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
        Chunk redText = new Chunk("This text is red. ", red);
        Font blue = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
        Chunk blueText = new Chunk("This text is blue and bold. ", blue);
        Font green = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GREEN);
        Chunk greenText = new Chunk("This text is green and italic. ", green);
        
        Paragraph p1 = new Paragraph(redText);
        document.add(p1);
        Paragraph p2 = new Paragraph();
        p2.add(blueText);
        p2.add(greenText);
        document.add(p2);
        
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(new Rectangle(36, 600, 144, 760));
        ct.addElement(p1);
        ct.addElement(p2);
        ct.go();
        
        document.close();
    }
}
