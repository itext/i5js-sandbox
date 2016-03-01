/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/35429441
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// No @WrapToTest because Windows fonts are used
public class ParseHtml14 {
    public static final String DEST = "results/xmlworker/test-herin2.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtml14().createPdf(DEST);
    }
        
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setInitialLeading(12);
        // step 3
        document.open();
        // step 4
        String html = "<b style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal; color: rgb(204, 255, 255); background-color: rgb(51, 102, 255);\">zzz&nbsp;</b>"
                + "<div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal;\"><b style=\"color: rgb(204, 255, 255); background-color: rgb(51, 102, 255);\"><br /></b></div>"
                + "<div><b style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal; color: rgb(204, 255, 255); background-color: rgb(51, 102, 255);\">Test &nbsp;</b> &nbsp; "
                + "<span style=\"color: rgb(255, 0, 0);\">&nbsp;<span style=\"font-weight: bold;\">Description</span> &nbsp;</span>"
                + "<span style=\"color: rgb(153, 51, 153); font-style: italic;\">Other Color</span>"
                + "</div>";
        ElementList elements = XMLWorkerHelper.parseToElementList(html, null);
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        for (Element e : elements) {
            cell.addElement(e);
        }
        table.addCell(cell);
        document.add(table);
        // step 5
        document.close();
    }
}
