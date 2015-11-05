/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/32765453/table-width-not-set-in-itextsharp-when-converting-html-to-pdf
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import sandbox.WrapToTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

//without @WrapToTest annotation, because there are no Windows fonts on the linux server (Calibri)
public class ParseHtmlTable12 {
    public static final String DEST = "results/xmlworker/html_table_12.pdf";
    public static final String HTML = "resources/xml/table12.html";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtmlTable12().createPdf(DEST);
    }
        
    /**
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
                new FileInputStream(HTML), Charset.forName("cp1252"),new XMLWorkerFontProvider("resources/fonts/"));
        // step 5
        document.close();
    }
}
