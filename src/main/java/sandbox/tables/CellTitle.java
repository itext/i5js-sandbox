/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/35746651
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
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class CellTitle {

    public static final String DEST = "results/tables/cell_title.pdf";
    
    class Title implements PdfPCellEvent {
        protected String title;
        
        public Title(String title) {
            this.title = title;
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            Chunk c = new Chunk(title);
            c.setBackground(BaseColor.LIGHT_GRAY);
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, 
                new Phrase(c), position.getLeft(5), position.getTop(5), 0);
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CellTitle().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = getCell("The title of this cell is title 1", "title 1");
        table.addCell(cell);
        cell = getCell("The title of this cell is title 2", "title 2");
        table.addCell(cell);
        cell = getCell("The title of this cell is title 3", "title 3");
        table.addCell(cell);
        document.add(table);
        document.close();
    }
    
    public PdfPCell getCell(String content, String title) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setCellEvent(new Title(title));
        cell.setPadding(5);
        return cell;
    }
    
}
