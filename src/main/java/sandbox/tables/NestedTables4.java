/*
 * This example was written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/36259214
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
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
public class NestedTables4 {
    
    public static final String DEST = "results/tables/nested_tables4.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables4().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(4);
        table.setTableEvent(new BorderEvent());
        table.setWidths(new int[]{1, 12, 8, 1});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        // first row
        PdfPCell cell = new PdfPCell(new Phrase("Main table"));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        table.addCell(cell);
        // second row
        table.addCell("");
        table.addCell("nested table 1");
        table.addCell("nested table 2");
        table.addCell("");
        // third row
        // third row cell 1
        table.addCell("");
        // third row cell 2
        PdfPTable table1 = new PdfPTable(1);
        table1.addCell("cell 1 of nested table 1");
        table1.addCell("cell 2 of nested table 1");
        table1.addCell("cell 2 of nested table 1");
        table.addCell(new PdfPCell(table1));
        // third row cell 3
        PdfPTable table2 = new PdfPTable(2);
        table2.getDefaultCell().setMinimumHeight(10);
        table2.addCell("");
        table2.addCell("");
        cell = new PdfPCell(new Phrase("cell 2 of nested table 2"));
        cell.setColspan(2);
        table2.addCell(cell);
        cell = new PdfPCell(new Phrase("cell 3 of nested table 2"));
        cell.setColspan(2);
        table2.addCell(cell);
        table.addCell(new PdfPCell(table2));
        // third row cell 4
        table.addCell("");
        // fourth row
        cell = new PdfPCell();
        cell.setColspan(4);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setMinimumHeight(16);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
    
    public class BorderEvent implements PdfPTableEvent {
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            float width[] = widths[0];
            float x1 = width[0];
            float x2 = width[width.length - 1];
            float y1 = heights[0];
            float y2 = heights[heights.length - 1];
            PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
            cb.rectangle(x1, y1, x2 - x1, y2 - y1);
            cb.stroke();
        }
    }
}
