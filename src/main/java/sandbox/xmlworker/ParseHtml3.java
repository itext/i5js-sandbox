/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29121766/certain-html-entities-arrows-are-not-rendered-in-pdf-itext
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import sandbox.WrapToTest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

//without @WrapToTest annotation, because there are no Windows fonts on the linux server (Arial)
public class ParseHtml3 {
    public static final String DEST = "results/xmlworker/html_3.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtml3().createPdf(DEST);
    }
    
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        String str = "<html><head></head><body style=\"font-size:12.0pt; font-family:Arial\">"+
                "<p>Special symbols: &larr;  &darr; &harr; &uarr; &rarr; &euro; &copy;</p>" +
                "</body></html>";
        
        XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
        InputStream is = new ByteArrayInputStream(str.getBytes());
        worker.parseXHtml(writer, document, is,Charset.forName("cp1252"),new XMLWorkerFontProvider("resources/fonts/"));
        // step 5
        document.close();
    }
}
