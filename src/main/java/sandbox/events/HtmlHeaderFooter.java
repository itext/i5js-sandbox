/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/33394158/add-header-to-pdf-from-html-using-itext
 */
package sandbox.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class HtmlHeaderFooter {
    public static final String DEST = "results/events/html_header_footer.pdf";
    public static final String HEADER = "<table width=\"100%\" border=\"0\"><tr><td>Header</td><td align=\"right\">Some title</td></tr></table>";
    public static final String FOOTER = "<table width=\"100%\" border=\"0\"><tr><td>Footer</td><td align=\"right\">Some title</td></tr></table>";
    
    public class HeaderFooter extends PdfPageEventHelper {
        protected ElementList header;
        protected ElementList footer;
        public HeaderFooter() throws IOException {
            header = XMLWorkerHelper.parseToElementList(HEADER, null);
            footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
        }
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                ColumnText ct = new ColumnText(writer.getDirectContent());
                ct.setSimpleColumn(new Rectangle(36, 832, 559, 810));
                for (Element e : header) {
                    ct.addElement(e);
                }
                ct.go();
                ct.setSimpleColumn(new Rectangle(36, 10, 559, 32));
                for (Element e : footer) {
                    ct.addElement(e);
                }
                ct.go();
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HtmlHeaderFooter().createPdf(DEST);
    }
    
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document(PageSize.A4, 36, 36, 36, 72);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        writer.setPageEvent(new HeaderFooter());
        // step 3
        document.open();
        // step 4
        for (int i = 0; i < 50; i++)
            document.add(new Paragraph("Hello World!"));
        document.newPage();
        document.add(new Paragraph("Hello World!"));
        document.newPage();
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();
    }
}
