/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27814701/itextsharp-how-to-use-htmlworker-to-covert-html-to-pdf-with-pagination
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import sandbox.WrapToTest;

/**
 *
 * @author iText
 */
@WrapToTest
public class ParseMultipleHtmlFiles2 {
    public static final String[] HTML = {
        "resources/xml/page1.html",
        "resources/xml/page2.html",
        "resources/xml/page3.html"
    };
    public static final String DEST = "results/xmlworker/multiple_html_pages2.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseMultipleHtmlFiles2().createPdf(DEST);
    }
        
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        String css = readCSS();
        for (String htmlfile : HTML) {
            String html = Utilities.readFileToString(htmlfile);
            ElementList list = XMLWorkerHelper.parseToElementList(html, css);
            for (Element e : list) {
                document.add(e);
            }
            document.newPage();
        }
        document.close();
    }
    
    private String readCSS() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        InputStream is = XMLWorkerHelper.class.getResourceAsStream("/default.css");
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return new String(baos.toByteArray());
    }
}
