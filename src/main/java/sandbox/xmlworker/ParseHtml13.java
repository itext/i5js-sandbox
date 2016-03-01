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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// No @WrapToTest because Windows fonts are used
public class ParseHtml13 {
    public static final String DEST = "results/xmlworker/test-herin.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtml13().createPdf(DEST);
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
        String html = "<div><span class=\"bluetextwhitebackground\">zzz</span></div>"
            + "<div>"
            + "<span class=\"bluetextwhitebackground\">Test</span> "
            + "<span class=\"redtext\">Description</span> "
            + "<span class=\"italicpurple\">Other Color</span>"
            + "</div>";
        String css = ".bluetextwhitebackground { font-family: times; color: white; background: blue}"
                + ".redtext { font-family: times; color: red; }"
                + ".italicpurple { font-family: times; font-style: italic; color: purple }";
        ElementList elements = XMLWorkerHelper.parseToElementList(html, css);
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
