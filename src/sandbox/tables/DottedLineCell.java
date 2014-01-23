package sandbox.tables;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.SandboxTest;

public class DottedLineCell extends SandboxTest {

    class DottedCells implements PdfPTableEvent {

        public void tableLayout(PdfPTable table, float[][] widths,
                                float[] heights, int headerRows, int rowStart,
                                PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            float llx = widths[0][0];
            float urx = widths[0][widths[0].length -1];
            for (int i = 0; i < heights.length; i++) {
                canvas.moveTo(llx, heights[i]);
                canvas.lineTo(urx, heights[i]);
            }
            for (int i = 0; i < widths.length; i++) {
                for (int j = 0; j < widths[i].length; j++) {
                    canvas.moveTo(widths[i][j], heights[i]);
                    canvas.lineTo(widths[i][j], heights[i+1]);
                }
            }
            canvas.stroke();
        }
    }

    class DottedCell implements PdfPCellEvent {

        public void cellLayout(PdfPCell cell, Rectangle position,
                               PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            canvas.rectangle(position.getLeft(), position.getBottom(),
                    position.getWidth(), position.getHeight());
            canvas.stroke();
        }
    }

    @Override
    protected String getOutPdf() {
        return "./results/tables/dotted_line_cell.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/tables/cmp_dotted_line_cell.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        DottedLineCell app = new DottedLineCell();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        document.add(new Paragraph("Table event"));
        PdfPTable table = new PdfPTable(3);
        table.setTableEvent(app.new DottedCells());
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.addCell("A1");
        table.addCell("A2");
        table.addCell("A3");
        table.addCell("B1");
        table.addCell("B2");
        table.addCell("B3");
        table.addCell("C1");
        table.addCell("C2");
        table.addCell("C3");
        document.add(table);
        document.add(new Paragraph("Cell event"));
        table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell(new Phrase("Test"));
        cell.setCellEvent(app.new DottedCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        document.add(table);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new DottedLineCell();
        test.makePdf();
    }
}
