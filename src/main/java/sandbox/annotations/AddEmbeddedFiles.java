/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27823189/add-multiple-attachments-in-a-pdf-using-itext-pdf-stamper
 */
package sandbox.annotations;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddEmbeddedFiles {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/annotations/hello_with_attachments.pdf";
    public static final String[] ATTACHMENTS = {
        "hello", "world", "what", "is", "up"
    };
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddEmbeddedFiles().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        for (String s : ATTACHMENTS) {
            PdfFileSpecification fs = PdfFileSpecification.fileEmbedded(
                stamper.getWriter(), null, String.format("%s.txt", s),
                String.format("Some test: %s", s).getBytes());
            stamper.addFileAttachment(String.format("Content: %s", s), fs);
        }
        stamper.close();
    }
}
