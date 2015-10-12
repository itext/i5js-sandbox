package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class DottedLineHeader {

    public static final String DEST = "results/tables/dotted_line_header.pdf";
    
    class DottedHeader implements PdfPTableEvent {

        public void tableLayout(PdfPTable table, float[][] widths,
            float[] heights, int headerRows, int rowStart,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            float x1 = widths[0][0];
            float x2 = widths[0][widths.length];
            canvas.moveTo(x1, heights[0]);
            canvas.lineTo(x2, heights[0]);
            canvas.moveTo(x1, heights[headerRows]);
            canvas.lineTo(x2, heights[headerRows]);
            canvas.stroke();
        }
    }
    
    class DottedCell implements PdfPCellEvent {
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            canvas.moveTo(position.getLeft(), position.getTop());
            canvas.lineTo(position.getRight(), position.getTop());
            canvas.moveTo(position.getLeft(), position.getBottom());
            canvas.lineTo(position.getRight(), position.getBottom());
            canvas.stroke();
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DottedLineHeader().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Table event"));
        PdfPTable table = new PdfPTable(3);
        table.setTableEvent(new DottedHeader());
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.addCell("A1");
        table.addCell("A2");
        table.addCell("A3");
        table.setHeaderRows(1);
        table.addCell("B1");
        table.addCell("B2");
        table.addCell("B3");
        table.addCell("C1");
        table.addCell("C2");
        table.addCell("C3");
        document.add(table);
        document.add(new Paragraph("Cell event"));
        table = new PdfPTable(3);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setCellEvent(new DottedCell());
        table.addCell("A1");
        table.addCell("A2");
        table.addCell("A3");
        table.getDefaultCell().setCellEvent(null);
        table.addCell("B1");
        table.addCell("B2");
        table.addCell("B3");
        table.addCell("C1");
        table.addCell("C2");
        table.addCell("C3");
        document.add(table);
        document.close();
    }
}
