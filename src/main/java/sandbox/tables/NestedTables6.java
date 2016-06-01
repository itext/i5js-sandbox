/*
 * This example was written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/37548146
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
public class NestedTables6 {
    
    public static final String DEST = "results/tables/nested_tables6.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables6().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(new Rectangle(1200, 800));
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // Header part
        PdfPTable mainTable = new PdfPTable(1);
        mainTable.getDefaultCell().setPadding(0);
        mainTable.setTotalWidth(1000);
        mainTable.setLockedWidth(true);
        PdfPTable subTable1 = new PdfPTable(5);
        subTable1.setTotalWidth(new float[]{200, 200, 200, 100, 300});
        subTable1.setLockedWidth(true);
        subTable1.addCell("test 1");
        subTable1.addCell("test 2");
        subTable1.addCell("test 3");
        subTable1.addCell("test 4");
        subTable1.addCell("test 5");
        mainTable.addCell(subTable1);
        PdfPTable subTable2 = new PdfPTable(5);
        subTable2.setTotalWidth(new float[]{200, 100, 200, 200, 300});
        subTable2.setLockedWidth(true);
        subTable2.addCell("test 1");
        subTable2.addCell("test 2");
        subTable2.addCell("test 3");
        subTable2.addCell("test 4");
        subTable2.addCell("test 5");
        PdfPCell cell2 = new PdfPCell(subTable2);
        mainTable.addCell(cell2);
        PdfPTable subTable3 = new PdfPTable(5);
        subTable3.setTotalWidth(new float[]{200, 200, 100, 200, 300});
        subTable3.setLockedWidth(true);
        subTable3.addCell("test 1");
        subTable3.addCell("test 2");
        subTable3.addCell("test 3");
        subTable3.addCell("test 4");
        subTable3.addCell("test 5");
        PdfPCell cell3 = new PdfPCell();
        cell3.setPadding(0);
        cell3.addElement(subTable3);
        mainTable.addCell(cell3);
        document.add(mainTable);
        document.close();
    }
}
