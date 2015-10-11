/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29702986/how-to-check-a-checkbox-in-pdf-file-with-the-same-variable-name-with-itext-and-j
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class FillHybridForm {
    
    public static final String SRC = "resources/pdfs/f8966.pdf";
    public static final String DEST = "results/acroforms/f8966_filled.pdf";
    
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillHybridForm().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        form.removeXfa();
        form.setField("topmostSubform[0].Page2[0].p2_cb01[0]", "1");
        form.setField("topmostSubform[0].Page2[0].p2_cb01[1]", "2");
        form.setField("topmostSubform[0].Page2[0].p2_cb01[2]", "3");
        form.setField("topmostSubform[0].Page2[0].p2_cb01[3]", "4");
        form.setField("topmostSubform[0].Page2[0].p2_cb01[4]", "5");
        form.setField("topmostSubform[0].Page2[0].p2_cb01[5]", "6");
        stamper.close();
        reader.close();
    }
}
