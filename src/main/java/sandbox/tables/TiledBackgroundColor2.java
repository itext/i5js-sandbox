/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34177025
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class TiledBackgroundColor2 {
    
    class TiledImageBackground implements PdfPCellEvent {

        protected Image image;
        
        public TiledImageBackground(Image image) {
            this.image = image;
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position,
                PdfContentByte[] canvases) {
            try {
                PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
                image.scaleToFit(10000000, position.getHeight());
                float x = position.getLeft();
                float y = position.getBottom();
                while (x + image.getScaledWidth() < position.getRight()) {
                    image.setAbsolutePosition(x, y);
                    cb.addImage(image);
                    x += image.getScaledWidth();
                }
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            }
        }
        
    }
    
    public static final String DEST = "results/tables/tiled_patterncolor2.pdf";
    public static final String IMG = "resources/images/bulb.gif";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TiledBackgroundColor2().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.addCell("Behold a cell with an image pattern:");
        PdfPCell cell = new PdfPCell();
        Image image = Image.getInstance(IMG);
        cell.setCellEvent(new TiledImageBackground(image));
        cell.setFixedHeight(60);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
