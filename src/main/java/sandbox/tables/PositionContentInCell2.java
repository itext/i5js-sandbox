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
public class PositionContentInCell2 {
    public static final String DEST = "results/tables/position_content_in_cell2.pdf";
    public static final String IMG = "resources/images/info.png";
    
    class ImageEvent implements PdfPCellEvent {
        protected Image img;
        public ImageEvent(Image img) {
            this.img = img;
        }
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            img.scaleAbsolute(position.getWidth(), position.getHeight());
            img.setAbsolutePosition(position.getLeft(), position.getBottom());
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
        protected float wPct;
        protected float hPct;
        protected int alignment;
        
        public PositionEvent(Phrase content, float wPct, float hPct, int alignment) {
            this.content = content;
            this.wPct = wPct;
            this.hPct = hPct;
            this.alignment = alignment;
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            float x = position.getLeft() + wPct * position.getWidth();
            float y = position.getBottom() + hPct * (position.getHeight() - content.getLeading());
            ColumnText.showTextAligned(canvas, alignment, content, x, y, 0);
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PositionContentInCell2().createPdf(DEST);
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
        PdfPCell cell5 = new PdfPCell();
        PdfPCell cell6 = new PdfPCell();
        PdfPCell cell7 = new PdfPCell();
        PdfPCell cell8 = new PdfPCell();
        // 2. Inside that table, make each cell with specific height:
        cell1.setFixedHeight(50);
        cell2.setFixedHeight(50);
        cell3.setFixedHeight(50);
        cell4.setFixedHeight(50);
        cell5.setFixedHeight(50);
        cell6.setFixedHeight(50);
        cell7.setFixedHeight(50);
        cell8.setFixedHeight(50);
        // 3. Each cell has the same background image
        ImageEvent imgEvent = new ImageEvent(Image.getInstance(IMG));
        cell1.setCellEvent(imgEvent);
        cell2.setCellEvent(imgEvent);
        cell3.setCellEvent(imgEvent);
        cell4.setCellEvent(imgEvent);
        cell5.setCellEvent(imgEvent);
        cell6.setCellEvent(imgEvent);
        cell7.setCellEvent(imgEvent);
        cell8.setCellEvent(imgEvent);
        // 4. Add text in front of the image at specific position
        cell1.setCellEvent(new PositionEvent(new Phrase(14, "Top left"), 0, 1, Element.ALIGN_LEFT));
        cell2.setCellEvent(new PositionEvent(new Phrase(14, "Top right"), 1, 1, Element.ALIGN_RIGHT));
        cell3.setCellEvent(new PositionEvent(new Phrase(14, "Top center"), 0.5f, 1, Element.ALIGN_CENTER));
        cell4.setCellEvent(new PositionEvent(new Phrase(14, "Bottom center"), 0.5f, 0, Element.ALIGN_CENTER));
        cell5.setCellEvent(new PositionEvent(new Phrase(14, "Middle center"), 0.5f, 0.5f, Element.ALIGN_CENTER));
        cell6.setCellEvent(new PositionEvent(new Phrase(14, "Middle center"), 0.5f, 0.5f, Element.ALIGN_CENTER));
        cell7.setCellEvent(new PositionEvent(new Phrase(14, "Bottom left"), 0, 0, Element.ALIGN_LEFT));
        cell8.setCellEvent(new PositionEvent(new Phrase(14, "Bottom right"), 1, 0, Element.ALIGN_RIGHT));
        // Wrap it all up!
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        document.add(table);
        document.close();
    }
}
