/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.columntext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
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
public class DropTablePart {
    public static final String DEST = "results/columntext/drop_table_part.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DropTablePart().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Rectangle column = new Rectangle(36, 36, 559, 806);
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(column);
        for (int i = 0; i < 4; ) {
            PdfPTable table = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("inner table " + (++i)));
            cell.setColspan(4);
            table.addCell(cell);
            for (int j = 0; j < 18; j++) {
                table.addCell(new Phrase("test Data " + (j + 1) + ".1"));
                table.addCell(new Phrase("test Data " + (j + 1) + ".1"));
                table.addCell(new Phrase("test Data " + (j + 1) + ".1"));
                table.addCell(new Phrase("test Data " + (j + 1) + ".1"));
            }
            ct.addElement(table);
            if (ColumnText.hasMoreText(ct.go())) {
                document.newPage();
                ct = new ColumnText(writer.getDirectContent());
                ct.setSimpleColumn(column);
            }
        }
        document.close();
    }
}
