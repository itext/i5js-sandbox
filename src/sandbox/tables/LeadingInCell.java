/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20145742/spacing-leading-pdfpcells-elements
 * 
 * Cell in composite mode, containing different paragraphs with a different leading.
 */
package sandbox.tables;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.SandboxTest;

public class LeadingInCell extends SandboxTest {

    @Override
    protected String getOutPdf() {
        return "./results/tables/leading_in_cell.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/tables/cmp_leading_in_cell.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        Paragraph p;
        p = new Paragraph(16, "paragraph 1: leading 16");
        cell.addElement(p);
        p = new Paragraph(32, "paragraph 2: leading 32");
        cell.addElement(p);
        p = new Paragraph(10, "paragraph 3: leading 10");
        cell.addElement(p);
        p = new Paragraph(18, "paragraph 4: leading 18");
        cell.addElement(p);
        p = new Paragraph(40, "paragraph 5: leading 40");
        cell.addElement(p);
        table.addCell(cell);
        document.add(table);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new LeadingInCell();
        test.makePdf();
    }
}
