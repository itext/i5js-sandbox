/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27814701/itextsharp-how-to-use-htmlworker-to-covert-html-to-pdf-with-pagination
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayOutputStream;
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
public class ParseMultipleHtmlFiles1 {
    public static final String[] HTML = {
        "resources/xml/page1.html",
        "resources/xml/page2.html",
        "resources/xml/page3.html"
    };
    public static final String DEST = "results/xmlworker/multiple_html_pages1.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseMultipleHtmlFiles1().createPdf(DEST);
    }
        
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
        document.open();
        PdfReader reader;
        for (String html : HTML) {
            reader = new PdfReader(parseHtml(html));
            copy.addDocument(reader);
            reader.close();
        }
        document.close();
    }
    
    public byte[] parseHtml(String html) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(html));
        // step 5
        document.close();
        // return the bytes of the PDF
        return baos.toByteArray();
    }
}
