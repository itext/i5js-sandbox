/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19703715/centered-text-in-itext-pdf-table-cell
 * 
 * We create a table with a single column and a single cell.
 * We add some content that needs to be centered vertically.
 */
package sandbox.tables;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.SandboxTest;

public class CenteredTextInCell extends SandboxTest {

    @Override
    protected String getOutPdf() {
        return "./results/tables/centered_text.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/tables/cmp_centered_text.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        Paragraph para = new Paragraph("Test", font);
        para.setLeading(0, 1);
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell();
        cell.setMinimumHeight(50);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.addElement(para);
        table.addCell(cell);
        document.add(table);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new CenteredTextInCell();
        test.makePdf();
    }
}
