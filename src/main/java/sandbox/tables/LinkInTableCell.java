/*
 * This example was written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/35288194
 */
package sandbox.tables;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
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
public class LinkInTableCell {
    public static final String DEST = "results/tables/link_in_table_cell.pdf";

    class LinkInCell implements PdfPCellEvent {
        protected String url;
        public LinkInCell(String url) {
            this.url = url;
        }
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfWriter writer = canvases[0].getPdfWriter();
            PdfAction action = new PdfAction(url);
            PdfAnnotation link = PdfAnnotation.createLink(
                writer, position, PdfAnnotation.HIGHLIGHT_INVERT, action);
            writer.addAnnotation(link);
        }
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        // Part of the content is a link:
        Phrase phrase = new Phrase();
        phrase.add("The founders of iText are nominated for a ");
        Chunk chunk = new Chunk("European Business Award!");
        chunk.setAnchor("http://itextpdf.com/blog/european-business-award-kick-ceremony");
        phrase.add(chunk);
        table.addCell(phrase);
        // The complete cell is a link:
        PdfPCell cell = new PdfPCell(new Phrase("Help us win a European Business Award!"));
        cell.setCellEvent(new LinkInCell("http://itextpdf.com/blog/help-us-win-european-business-award"));
        table.addCell(cell);
        document.add(table);
        document.close();
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new LinkInTableCell().createPdf(DEST);
    }
}