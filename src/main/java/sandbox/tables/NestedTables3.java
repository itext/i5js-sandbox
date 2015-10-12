/*
 * This example was written by Bruno Lowagie in answer to the following questions:
 * http://stackoverflow.com/questions/31108488/pdfptable-header-repeat-when-data-in-a-different-table-increases-in-itext
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
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
 * @author iText
 */
@WrapToTest
public class NestedTables3 {
    
    class MyPdfPTableEvent implements PdfPTableEvent {

        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
        }

        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            ColumnText ct = new ColumnText(canvases[PdfPTable.TEXTCANVAS]);
            for (int i = 1; i < widths[1].length; i++) {
                Rectangle position = new Rectangle(widths[1][i - 1], heights[1], widths[1][i], heights[2]);
                ct.setSimpleColumn(position);
                ct.addText(new Phrase("This inner table header will always be repeated"));
                try {
                    ct.go();
                } catch (DocumentException ex) {
                }
            }
        }
    }
    
    public static final String DEST = "results/tables/nested_tables3.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables3().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setTableEvent(new MyPdfPTableEvent());
        PdfPCell cell = new PdfPCell(
            new Phrase("This outer header is repeated on every page"));
        cell.setColspan(2);
        table.addCell(cell);
        table.setHeaderRows(1);
        PdfPTable inner1 = new PdfPTable(1);
        cell = new PdfPCell();
        cell.setFixedHeight(20);
        inner1.addCell(cell);
        cell = new PdfPCell(
            new Phrase("This inner header won't be repeated on every page"));
        inner1.addCell(cell);
        inner1.setHeaderRows(2);
        for (int i = 0; i < 10; i++) {
            inner1.addCell("test");
        }
        cell = new PdfPCell(inner1);
        table.addCell(cell);
        PdfPTable inner2 = new PdfPTable(1);
        cell = new PdfPCell();
        cell.setFixedHeight(20);
        inner2.addCell(cell);
        cell = new PdfPCell(
            new Phrase("This inner may be repeated on every page"));
        inner2.addCell(cell);
        inner2.setHeaderRows(2);
        for (int i = 0; i < 35; i++) {
            inner2.addCell("test");
        }
        cell = new PdfPCell(inner2);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
