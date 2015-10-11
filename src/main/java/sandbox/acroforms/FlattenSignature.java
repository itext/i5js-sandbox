/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/28163159/about-visible-digital-signatures
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class FlattenSignature {

    public static final String SRC = "resources/pdfs/input_signed.pdf";
    public static final String DEST = "results/acroforms/output_no_longer_signed.pdf";
    

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FlattenSignature().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.setFormFlattening(true);
        stamper.close();
    }
}
