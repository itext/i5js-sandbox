/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29237980/itext-pdf-not-displaying-chinese-characters-when-using-noto-fonts
 */
package sandbox.fonts;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class NotoExample {
        
    public static final String DEST = "results/fonts/chinese.pdf";
    public static final String FONT = "resources/fonts/NotoSansCJKsc-Regular.otf";
    public static final String TEXT = "These are the protagonists in 'Hero', a movie by Zhang Yimou:\n"
        + "\u7121\u540d (Nameless), \u6b98\u528d (Broken Sword), "
        + "\u98db\u96ea (Flying Snow), \u5982\u6708 (Moon), "
        + "\u79e6\u738b (the King), and \u9577\u7a7a (Sky).";
    public static final String CHINESE = "\u5341\u950a\u57cb\u4f0f";
    public static final String JAPANESE = "\u8ab0\u3082\u77e5\u3089\u306a\u3044";
    public static final String KOREAN = "\ube48\uc9d1";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NotoExample().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Paragraph p = new Paragraph(TEXT, font);
        document.add(p);
        document.add(new Paragraph(CHINESE, font));
        document.add(new Paragraph(JAPANESE, font));
        document.add(new Paragraph(KOREAN, font));
        document.close();
    }
}
