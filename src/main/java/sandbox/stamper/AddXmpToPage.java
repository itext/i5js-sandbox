/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28427100/how-do-i-add-xmp-metadata-to-each-page-of-an-existing-pdf-using-itextsharp
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.xml.xmp.XmpWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddXmpToPage {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/stamper/hello_with_page_xmp.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddXmpToPage().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfDictionary page = reader.getPageN(1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmpWriter xmp = new XmpWriter(baos, new PdfDictionary());
        xmp.close();
        PdfIndirectObject ref = stamper.getWriter().addToBody(new PdfStream(baos.toByteArray()));
        page.put(PdfName.METADATA, ref.getIndirectReference());
        stamper.close();
        reader.close();
    }
}
