/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27591313/how-to-restrict-number-of-characters-in-a-phrase-in-pdf
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class UnderlineParagraphWithTwoParts {
    public static final String DEST = "results/objects/underline_paragraph_with_two_parts.pdf";
    public static final Chunk GLUE = new Chunk(new VerticalPositionMark());
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new UnderlineParagraphWithTwoParts().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        float charWidth = bf.getWidth(" ");
        int charactersPerLine = 101;
        float pageWidth = document.right() - document.left();
        float fontSize = (1000 * (pageWidth / (charWidth * charactersPerLine)));
        fontSize = ((int)(fontSize * 100)) / 100f;
        Font font = new Font(bf, fontSize);
        String string2 = "0123456789";
        for (int i = 0; i < 5; i++)
            string2 = string2 + string2;
        addParagraphWithTwoParts1(document, font, "0123", string2);
        document.add(new Paragraph("Test 1"));
        addParagraphWithTwoParts2(document, font, "0123", string2);
        document.add(new Paragraph("Test 1"));
        document.add(new Paragraph("Test 1"));
        addParagraphWithTwoParts1(document, font, "012345", string2);
        document.add(new Paragraph("Test 2"));
        addParagraphWithTwoParts2(document, font, "012345", string2);
        document.add(new Paragraph("Test 2"));
        addParagraphWithTwoParts1(document, font, "0123456789012345", string2);
        document.add(new Paragraph("Test 3"));
        document.add(new Paragraph("Test 3"));
        addParagraphWithTwoParts2(document, font, "0123456789012345", string2);
        document.add(new Paragraph("Test 3"));
        document.add(new Paragraph("Test 3"));
        addParagraphWithTwoParts1(document, font, "012", "0123456789");
        document.add(new Paragraph("Test 4"));
        addParagraphWithTwoParts2(document, font, "012", "0123456789");
        document.add(new Paragraph("Test 4"));
        addParagraphWithTwoParts1(document, font, "012345", "01234567890123456789");
        document.add(new Paragraph("Test 5"));
        addParagraphWithTwoParts2(document, font, "012345", "01234567890123456789");
        document.add(new Paragraph("Test 5"));
        addParagraphWithTwoParts1(document, font, "0", "01234567890123456789012345678901234567890123456789");
        document.add(new Paragraph("Test 6"));
        addParagraphWithTwoParts2(document, font, "0", "01234567890123456789012345678901234567890123456789");
        document.close();
    }
    
    public void addParagraphWithTwoParts1(Document document, Font font, String string1, String string2)
            throws DocumentException {
        if (string1 == null) string1 = "";
        if (string1.length() > 10)
            string1 = string1.substring(0, 10);
        Chunk chunk1 = new Chunk(string1, font);
        if (string2 == null) string2 = "";
        if (string1.length() + string2.length() > 100)
            string2 = string2.substring(0, 100 - string1.length());
        Chunk chunk2 = new Chunk(string2, font);
        Paragraph p = new Paragraph();
        p.add(chunk1);
        p.add(GLUE);
        p.add(chunk2);
        LineSeparator line = new LineSeparator();
        line.setOffset(-2);
        p.add(line);
        document.add(p);
    }
    
    public void addParagraphWithTwoParts2(Document document, Font font, String string1, String string2)
            throws DocumentException {
        if (string1 == null) string1 = "";
        if (string1.length() > 10)
            string1 = string1.substring(0, 10);
        if (string2 == null) string2 = "";
        if (string1.length() + string2.length() > 100)
            string2 = string2.substring(0, 100 - string1.length());
        Paragraph p = new Paragraph(string1 + " " + string2, font);
        LineSeparator line = new LineSeparator();
        line.setOffset(-2);
        p.add(line);
        document.add(p);
    }
}
