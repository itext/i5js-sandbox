/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/36253087
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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

/**
 * @author Bruno Lowagie (iText Software)
 */
public class DiagonalCell {
    
    public static final String DEST = "results/tables/diagonal_cell.pdf";
    
    class Diagonal implements PdfPCellEvent {
        protected String columns;
        protected String rows;
        
        public Diagonal(String columns, String rows) {
            this.columns = columns;
            this.rows = rows;
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, 
                new Phrase(columns), position.getRight(2), position.getTop(12), 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, 
                new Phrase(rows), position.getLeft(2), position.getBottom(2), 0);
            canvas = canvases[PdfPTable.LINECANVAS];
            canvas.moveTo(position.getLeft(), position.getTop());
            canvas.lineTo(position.getRight(), position.getBottom());
            canvas.stroke();
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DiagonalCell().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(6);
        table.getDefaultCell().setMinimumHeight(30);
        PdfPCell cell = new PdfPCell();
        cell.setCellEvent(new Diagonal("Gravity", "Occ"));
        table.addCell(cell);
        table.addCell("1");
        table.addCell("2");
        table.addCell("3");
        table.addCell("4");
        table.addCell("5");
        for (int i = 0; i < 5; ) {
            table.addCell(String.valueOf(++i));
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("");
        }
        document.add(table);
        document.close();
    }
}
