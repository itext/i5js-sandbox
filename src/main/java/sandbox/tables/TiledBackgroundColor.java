/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34177025
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPatternPainter;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class TiledBackgroundColor {
    
    public static final String DEST = "results/tables/tiled_patterncolor.pdf";
    public static final String IMG = "resources/images/bulb.gif";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TiledBackgroundColor().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        Image image = Image.getInstance(IMG);
        PdfPatternPainter img_pattern = canvas.createPattern(
                image.getScaledWidth(), image.getScaledHeight());
        image.setAbsolutePosition(0, 0);
        img_pattern.addImage(image);
        BaseColor color = new PatternColor(img_pattern);
        PdfPTable table = new PdfPTable(2);
        table.addCell("Behold a cell with an image pattern:");
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(60);
        cell.setBackgroundColor(color);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
