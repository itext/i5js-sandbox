/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19999048/how-to-create-hyperlink-from-a-pdf-to-another-pdf-to-a-specified-page-using-itex
 * 
 * Creating a link from one PDF to another
 */
package sandbox.annotations;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Before;
import sandbox.SandboxTest;

public class RemoteGoto extends SandboxTest {

    String outPdf1 = "./results/annotations/subdir/abc.pdf";
    String cmpPdf1 = "./resources/results/annotations/subdir/cmp_abc.pdf";
    String outPdf2 = "./results/annotations/xyz.pdf";
    String cmpPdf2 = "./resources/results/annotations/cmp_xyz.pdf";

    @Before
    @Override
    public void setup() {
        super.setup();
        new File("./results/annotations/subdir").mkdirs();
    }

    @Override
    protected String getOutPdf() {
        return outPdf1;
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        // first document
        Document document1 = new Document();
        PdfWriter.getInstance(document1, new FileOutputStream(outPdf1));
        document1.open();
        Anchor anchor = new Anchor("This is a destination");
        anchor.setName("dest");
        document1.add(anchor);
        document1.close();
        // second document (with a link to the first one)
        Document document2 = new Document();
        PdfWriter.getInstance(document2, new FileOutputStream(outPdf2));
        document2.open();
        Chunk chunk = new Chunk("Link");
        chunk.setAction(PdfAction.gotoRemotePage("subdir/abc.pdf", "dest", false,  true));
        document2.add(chunk);
        document2.close();
    }

    @Override
    protected void comparePdf(String outPdf, String cmpPdf) throws Exception {
        super.comparePdf(outPdf1, cmpPdf1);
        super.comparePdf(outPdf2, cmpPdf2);
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new RemoteGoto();
        test.makePdf();
    }
}
