/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/31169268/cell-background-image-with-text-itextsharp
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
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
public class PositionContentInCell {
    public static final String DEST = "results/tables/position_content_in_cell.pdf";
    public static final String IMG = "resources/images/info.png";
    public enum POSITION { TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT };
    
    class ImageEvent implements PdfPCellEvent {
        protected Image img;
        public ImageEvent(Image img) {
            this.img = img;
        }
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            img.scaleToFit(position.getWidth(), position.getHeight());
            img.setAbsolutePosition(position.getLeft() + (position.getWidth() - img.getScaledWidth()) / 2,
                    position.getBottom() + (position.getHeight() - img.getScaledHeight()) / 2);
            PdfContentByte canvas = canvases[PdfPTable.BACKGROUNDCANVAS];
            try {
                canvas.addImage(img);
            } catch (DocumentException ex) {
                // do nothing
            }
        }
    }
    
    class PositionEvent implements PdfPCellEvent {
        protected Phrase content;
        protected POSITION pos;
        
        public PositionEvent(Phrase content, POSITION pos) {
            this.content = content;
            this.pos = pos;
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            float x = 0;
            float y = 0;
            int alignment = 0;
            switch (pos) {
                case TOP_LEFT:
                    x = position.getLeft(3);
                    y = position.getTop(content.getLeading());
                    alignment = Element.ALIGN_LEFT;
                    break;
                case TOP_RIGHT:
                    x = position.getRight(3);
                    y = position.getTop(content.getLeading());
                    alignment = Element.ALIGN_RIGHT;
                    break;
                case BOTTOM_LEFT:
                    x = position.getLeft(3);
                    y = position.getBottom(3);
                    alignment = Element.ALIGN_LEFT;
                    break;
                case BOTTOM_RIGHT:
                    x = position.getRight(3);
                    y = position.getBottom(3);
                    alignment = Element.ALIGN_RIGHT;
                    break;
            }
            ColumnText.showTextAligned(canvas, alignment, content, x, y, 0);
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PositionContentInCell().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // 1. Create a Document which contains a table:
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell1 = new PdfPCell();
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell();
        PdfPCell cell4 = new PdfPCell();
        // 2. Inside that table, make each cell with specific height:
        cell1.setFixedHeight(50);
        cell2.setFixedHeight(50);
        cell3.setFixedHeight(50);
        cell4.setFixedHeight(50);
        // 3. Each cell has the same background image
        ImageEvent imgEvent = new ImageEvent(Image.getInstance(IMG));
        cell1.setCellEvent(imgEvent);
        cell2.setCellEvent(imgEvent);
        cell3.setCellEvent(imgEvent);
        cell4.setCellEvent(imgEvent);
        // 4. Add text in front of the image at specific position
        cell1.setCellEvent(new PositionEvent(new Phrase("Top left"), POSITION.TOP_LEFT));
        cell2.setCellEvent(new PositionEvent(new Phrase("Top right"), POSITION.TOP_RIGHT));
        cell3.setCellEvent(new PositionEvent(new Phrase("Bottom left"), POSITION.BOTTOM_LEFT));
        cell4.setCellEvent(new PositionEvent(new Phrase("Bottom right"), POSITION.BOTTOM_RIGHT));
        // Wrap it all up!
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        document.add(table);
        document.close();
    }
}
