/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/34267096/
 */
package sandbox.graphics;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author iText
 */
public class DrawingLines {

    public static final String DEST = "results/graphics/draw_lines.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DrawingLines().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
        canvas.setColorStroke(magentaColor);
        canvas.moveTo(36, 36);
        canvas.lineTo(36, 806);
        canvas.lineTo(559, 36);
        canvas.lineTo(559, 806);
        canvas.closePathStroke();
        document.close();
    }
}
