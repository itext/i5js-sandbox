/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29102552/convert-html-to-pdf-by-itext-xmlworker-with-polish-letters
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

//without @WrapToTest annotation, because there are no Windows fonts on the linux server (Times New Roman)
public class ParseHtml2 {
    public static final String DEST = "results/xmlworker/html_2.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtml2().createPdf(DEST);
    }
    
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        String str = "<html><head></head><body style=\"font-size:12.0pt; font-family:Times New Roman\">"+
                "<a href='http://www.rgagnon.com/howto.html'><b>Real's HowTo</b></a>" +
                "<h1>Show your support</h1>" +
                "<p>It DOES cost a lot to produce this site - in ISP storage and transfer fees</p>" +
                "<p>TEST POLSKICH ZNAKÓW: \u0104\u0105\u0106\u0107\u00d3\u00f3\u0141\u0142\u0179\u017a\u017b\u017c\u017d\u017e\u0118\u0119</p>" +
                "<hr/>" +
                "<p>the huge amounts of time it takes for one person to design and write the actual content.</p>" +
                "<p>If you feel that effort has been useful to you, perhaps you will consider giving something back?</p>" +
                "<p>Donate using PayPal\u017d</p>" +
                "<p>Contributions via PayPal are accepted in any amount</p>" +
                "<p><br/><table border='1'><tr><td>Java HowTo</td></tr><tr>" +
                "<td style='background-color:red;'>Javascript HowTo</td></tr>" +
                "<tr><td>Powerbuilder HowTo</td></tr></table></p>" +
                "</body></html>";

        XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
        InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"), new XMLWorkerFontProvider("resources/fonts/"));
        // step 5
        document.close();
    }
}
