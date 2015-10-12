/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/31262460/how-to-add-x-offset-to-text-pattern-with-every-x-step-itext
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class TextPattern {
    
    public static final String DEST = "results/objects/text_pattern.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TextPattern().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont();
        String filltext = "this is the fill text! ";
        float filltextWidth = bf.getWidthPoint(filltext, 6);
        PdfPatternPainter pattern = canvas.createPattern(filltextWidth, 60, filltextWidth, 60);
        pattern.beginText();
        pattern.setFontAndSize(bf, 6.0f);
        float x = 0;
        for (float y = 0; y < 60; y += 10) {
            pattern.setTextMatrix(x - filltextWidth, y);
            pattern.showText(filltext);
            pattern.setTextMatrix(x, y);
            pattern.showText(filltext);
            x += (filltextWidth / 6);
        }
        pattern.endText();

        canvas.rectangle(0, 0, 595, 842);
        canvas.setPatternFill(pattern);
        canvas.fill();
        document.close();
    }
    
}
