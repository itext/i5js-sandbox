/*
 * This example was written in answer to:
 * http://stackoverflow.com/questions/34396579
 */
package sandbox.fonts;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;import sandbox.WrapToTest;
;

/**
 * @author iText
 */
@WrapToTest
public class FreeSansBold {
        
    public static final String DEST = "results/fonts/free_sans_bold.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    public static final String FONTBOLD = "resources/fonts/FreeSansBold.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FreeSansBold().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 12);
        Paragraph p = new Paragraph("FreeSans regular: \u0110", font);
        document.add(p);
        BaseFont bfbold = BaseFont.createFont(FONTBOLD, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font bold = new Font(bfbold, 12);
        p = new Paragraph("FreeSans bold: \u0110", bold);
        document.add(p);
        document.close();
    }
    
}
