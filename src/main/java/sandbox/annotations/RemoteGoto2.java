/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19999048/how-to-create-hyperlink-from-a-pdf-to-another-pdf-to-a-specified-page-using-itex
 * 
 * Creating a link from one PDF to another
 */
package sandbox.annotations;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class RemoteGoto2 {
    public static final String DEST = "results/annotations/subdir/abc.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        RemoteGoto2 app = new RemoteGoto2();
        app.createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // first document
        Document document1 = new Document();
        PdfWriter.getInstance(document1, new FileOutputStream(dest));
        document1.open();
        Anchor anchor = new Anchor("This is a destination");
        anchor.setName("dest");
        document1.add(anchor);
        document1.close();
    }
}
