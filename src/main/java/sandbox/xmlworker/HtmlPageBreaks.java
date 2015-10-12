/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27843338/converting-repeater-control-to-pdf-using-itextsharp-every-repeated-contents-shou
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 *
 * @author iText
 */
@WrapToTest
public class HtmlPageBreaks {
    public static final String DEST = "results/xmlworker/page_breaks.pdf";
    public static final String HTML = "resources/xml/page_breaks.html";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HtmlPageBreaks().createPdf(DEST);
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
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML));
        // step 5
        document.close();
    }
}
