package sandbox.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

import sandbox.WrapToTest;

@WrapToTest
public class SubTotal {
    public static final String DEST = "results/tables/subtotal.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SubTotal().createPdf(DEST);
    }

    class Totals {
    	double subtotal = 0;
    	double total = 0;
    }
    
    class SubTotalEvent implements PdfPCellEvent {

    	Double price;
    	Totals totals;
    	
    	public SubTotalEvent(Totals totals, double price) {
    		this.totals = totals;
    		this.price = price;
    	}
    	
    	public SubTotalEvent(Totals totals) {
    		this.totals = totals;
    		price = null;
    	}
    	
		@Override
		public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
			if (price == null) {
				PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
						new Phrase(String.valueOf(totals.subtotal)),
						position.getLeft() + 2, position.getBottom() + 2, 0);
				totals.subtotal = 0;
				return;
			}
			totals.subtotal += price;
			totals.total += price;
		}
    	
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
    	
    	Totals totals = new Totals();
    	
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{1, 1, 1, 3, 3});
        // header
        table.addCell("Pos");
        table.addCell("Menge");
        table.addCell("Text");
        table.addCell("Einzerpreis");
        table.addCell("Summe");
        // footer
        PdfPCell cell = new PdfPCell(new Phrase("Subtotal"));
        cell.setColspan(4);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.setCellEvent(new SubTotalEvent(totals));
        table.addCell(cell);
        // definitions
        table.setHeaderRows(2);
        table.setFooterRows(1);
        // table body
        for(int r = 0; r < 50; ){
            table.addCell(String.valueOf(++r));
            table.addCell("1");
            table.addCell("text");
            table.addCell("10.0");
            cell = new PdfPCell(new Phrase("10.0"));
            cell.setCellEvent(new SubTotalEvent(totals, 10));
            table.addCell(cell);
        }
        document.add(table);
        // extra footer
        table = new PdfPTable(5);
        table.setWidths(new int[]{1, 1, 1, 3, 3});
        cell = new PdfPCell(new Phrase("Grand total"));
        cell.setColspan(4);
        table.addCell(cell);
        table.addCell(String.valueOf(totals.total));
        document.add(table);
        document.close();
    }
}
