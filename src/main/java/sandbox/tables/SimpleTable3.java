/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27884229/itextsharap-error-while-adding-pdf-table-to-document
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class SimpleTable3 {
    public static final String DEST = "results/tables/simple_table3.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable3().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A3.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(35);
        table.setTotalWidth(document.getPageSize().getWidth() - 80);
        table.setLockedWidth(true);
        PdfPCell contractor = new PdfPCell(new Phrase("XXXXXXXXXXXXX"));
        contractor.setColspan(5);
        table.addCell(contractor);
        PdfPCell workType = new PdfPCell(new Phrase("Refractory Works"));
        workType.setColspan(5);
        table.addCell(workType);
        PdfPCell supervisor = new PdfPCell(new Phrase("XXXXXXXXXXXXXX"));
        supervisor.setColspan(4);
        table.addCell(supervisor);
        PdfPCell paySlipHead = new PdfPCell(new Phrase("XXXXXXXXXXXXXXXX"));
        paySlipHead.setColspan(10);
        table.addCell(paySlipHead);
        PdfPCell paySlipMonth = new PdfPCell(new Phrase("XXXXXXX"));
        paySlipMonth.setColspan(2);
        table.addCell(paySlipMonth);
        PdfPCell blank = new PdfPCell(new Phrase(""));
        blank.setColspan(9);
        table.addCell(blank);
        document.add(table);
        document.close();
    }

}
