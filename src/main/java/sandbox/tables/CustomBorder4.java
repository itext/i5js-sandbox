package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class CustomBorder4 {

    public static final String DEST = "results/tables/custom_border_4.pdf";
    
    abstract class CustomBorder implements PdfPCellEvent {
        private int border = 0;
        public CustomBorder(int border) {
            this.border = border;
        }
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.saveState();
            setLineDash(canvas);
            if ((border & PdfPCell.TOP) == PdfPCell.TOP) {
                canvas.moveTo(position.getRight(), position.getTop());
                canvas.lineTo(position.getLeft(), position.getTop());
            }
            if ((border & PdfPCell.BOTTOM) == PdfPCell.BOTTOM) {
                canvas.moveTo(position.getRight(), position.getBottom());
                canvas.lineTo(position.getLeft(), position.getBottom());
            }
            if ((border & PdfPCell.RIGHT) == PdfPCell.RIGHT) {
                canvas.moveTo(position.getRight(), position.getTop());
                canvas.lineTo(position.getRight(), position.getBottom());
            }
            if ((border & PdfPCell.LEFT) == PdfPCell.LEFT) {
                canvas.moveTo(position.getLeft(), position.getTop());
                canvas.lineTo(position.getLeft(), position.getBottom());
            }
            canvas.stroke();
            canvas.restoreState();
        }
        
        public abstract void setLineDash(PdfContentByte canvas);
    }
    
    class SolidBorder extends CustomBorder {
        public SolidBorder(int border) { super(border); }
        public void setLineDash(PdfContentByte canvas) {}
    }
    class DottedBorder extends CustomBorder {
        public DottedBorder(int border) { super(border); }
        public void setLineDash(PdfContentByte canvas) {
            canvas.setLineCap(PdfContentByte.LINE_CAP_ROUND);
            canvas.setLineDash(0, 4, 2);
        }
    }
    class DashedBorder extends CustomBorder {
        public DashedBorder(int border) { super(border); }
        public void setLineDash(PdfContentByte canvas) {
            canvas.setLineDash(3, 3);
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CustomBorder4().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        
        PdfPTable table;
        PdfPCell cell;
        
        table = new PdfPTable(4);
        table.setSpacingAfter(30);
        cell = new PdfPCell(new Phrase("dotted left border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedBorder(PdfPCell.LEFT));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("solid right border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new SolidBorder(PdfPCell.RIGHT));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("solid top border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new SolidBorder(PdfPCell.TOP));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("dashed bottom border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
        table.addCell(cell);
        document.add(table);
        
        table = new PdfPTable(4);
        table.setSpacingAfter(30);
        cell = new PdfPCell(new Phrase("dotted left and dashed top border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedBorder(PdfPCell.LEFT));
        cell.setCellEvent(new DashedBorder(PdfPCell.TOP));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("solid right and dotted bottom border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedBorder(PdfPCell.BOTTOM));
        cell.setCellEvent(new SolidBorder(PdfPCell.RIGHT));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("no border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("full border"));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new DottedBorder(PdfPCell.LEFT | PdfPCell.RIGHT));
        cell.setCellEvent(new SolidBorder(PdfPCell.TOP));
        cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
