/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19999048/how-to-create-hyperlink-from-a-pdf-to-another-pdf-to-a-specified-page-using-itex
 * 
 * Creating a link from one PDF to another
 */
package sandbox.annotations;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class RemoteGoto1 {
    public static final String SRC = "results/annotations/xyz.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(SRC);
        file.getParentFile().mkdirs();
        RemoteGoto1 app = new RemoteGoto1();
        app.createPdf(SRC);
    }
    
    public void createPdf(String src) throws IOException, DocumentException {
        // second document (with a link to the first one)
        Document document2 = new Document();
        PdfWriter.getInstance(document2, new FileOutputStream(src));
        document2.open();
        Chunk chunk = new Chunk("Link");
        chunk.setAction(PdfAction.gotoRemotePage("subdir/abc.pdf", "dest", false,  true));
        document2.add(chunk);
        document2.close();
    }
}
