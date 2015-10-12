/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/31268867/
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
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class MathSymbols {
    public static final String DEST = "results/fonts/math_symbols.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    public static final String TEXT = "this string contains special characters like this  \u2208, \u2229, \u2211, \u222b, \u2206";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MathSymbols().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 12);
        Paragraph p = new Paragraph(TEXT, f);
        document.add(p);
        document.close();
    }
}
