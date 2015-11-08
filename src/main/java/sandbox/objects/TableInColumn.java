package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class TableInColumn {

    public static final String DEST = "results/objects/table_in_column.pdf";
    
    private int status = ColumnText.START_COLUMN;
    private float y_position = 0;
    
    public static void main(String[] args) throws IOException, DocumentException {
        LoggerFactory.getInstance().setLogger(new SysoLogger());
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableInColumn().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte pagecanvas = writer.getDirectContent();
        PdfPTable table;
        PdfPCell cell = new PdfPCell();
        for (int i = 1; i <= 30; i++)
            cell.addElement(new Paragraph("Line " + i));
        table = new PdfPTable(1);
        table.setSplitLate(false);
        table.addCell(cell);
        addTable(document, pagecanvas, table);
        document.newPage();
        table = new PdfPTable(1);
        table.setSplitLate(false);
        table.addCell(cell);
        table.addCell(cell);
        addTable(document, pagecanvas, table);
        // step 5
        document.close();
    }

    public void addTable(Document document, PdfContentByte canvas, PdfPTable table) throws DocumentException {
        Rectangle pagedimension = new Rectangle(36, 36, 559, 806);
        System.out.println("Adding table to try");
        drawColumnText(document, canvas, pagedimension, table, true);
        Rectangle rect;
        if (ColumnText.hasMoreText(status)) {
            rect = pagedimension;
        }
        else {
            rect = new Rectangle(36, 36, 559, 806 - ((y_position - 36) / 2));
        }
        System.out.println("Adding table for real");
        drawColumnText(document, canvas, rect, table, false);
    }
    
    public void drawColumnText(Document document, PdfContentByte canvas, Rectangle rect, PdfPTable table, boolean simulate) throws DocumentException {
        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(rect);
        ct.addElement(table);
        status = ct.go(simulate);
        y_position = ct.getYLine();
        System.out.println("First go: " + status);
        while (!simulate && ColumnText.hasMoreText(status)) {
            document.newPage();
            ct.setSimpleColumn(rect);
            status = ct.go(simulate);
            System.out.println("Next go? " + status);
        }
        System.out.println("Final status: " + status);
    }
}
