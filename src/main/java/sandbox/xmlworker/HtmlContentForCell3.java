/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29390428/itext-why-the-last-item-of-an-unordered-html-bullet-list-is-not-showing-on-my-p
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class HtmlContentForCell3 {
    
    public static final String HTML = "<html>" +
        " <head></head>" +
        " <body>" +
        "  <span>List</span>" +
        "  <ul>" +
        "   <li>document2.txt.txt (23 B)</li>" +
        "   <li>document1.txt.txt (12 B)</li>" +
        "   <li>document3.txt.txt (27 B)</li>" +
        "  </ul>" +
        " </body>" +
        "</html>";
    public static final String DEST = "results/xmlworker/html_in_cell3.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HtmlContentForCell3().createPdf(DEST);
    }

    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        PdfPTable table = new PdfPTable(2);
        table.addCell("Some rich text:");
        PdfPCell cell = new PdfPCell();
        for (Element e : XMLWorkerHelper.parseToElementList(HTML, null)) {
            cell.addElement(e);
        }
        table.addCell(cell);
        document.add(table);
        // step 5
        document.close();
    }

}
