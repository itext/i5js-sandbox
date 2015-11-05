/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/28233592/html-to-pdf-using-itext-or-flying-saucer
 */
package sandbox.xmlworker;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
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

/**
 *
 * @author iText
 */
//without @WrapToTest annotation, because there are no Windows fonts on the linux server (Calibri)
public class ParseHtmlTable6 {
    public static final String DEST = "results/xmlworker/html_table_6.pdf";
    public static final String HTML = "resources/xml/paragj.html";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtmlTable6().createPdf(DEST);
    }
        
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Rectangle pagesize = new Rectangle(PageSize.A4);
        pagesize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xEE));
        Document document = new Document(pagesize);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML),Charset.forName("cp1252"),new XMLWorkerFontProvider("resources/fonts/"));
        // step 5
        document.close();
    }
}
