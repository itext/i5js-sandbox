/**
 * This example was written by Bruno Lowagie in answer to the following questions:
 * http://stackoverflow.com/questions/28503491/large-table-in-table-cell-invoke-page-break
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class NestedTables2 {
    public static final String DEST = "results/tables/nested_tables2.pdf";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables2().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setSplitLate(false);
        table.setWidths(new int[]{1, 15});
        for (int i = 1; i <= 20; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("It is not smart to use iText 2.1.7!");
        }
        PdfPTable innertable = new PdfPTable(2);
        innertable.setWidths(new int[]{1, 15});
        for (int i = 0; i < 90; i++) {
            innertable.addCell(String.valueOf(i + 1));
            innertable.addCell("Upgrade if you're a professional developer!");
        }
        table.addCell("21");
        table.addCell(innertable);
        for (int i = 22; i <= 40; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("It is not smart to use iText 2.1.7!");
        }
        document.add(table);
        document.close();
    }
}
