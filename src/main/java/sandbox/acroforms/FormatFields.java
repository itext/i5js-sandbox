/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27711035/itextsharp-acrofields-format-as-number
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
public class FormatFields {

    public static final String SRC = "resources/pdfs/form.pdf";
    public static final String DEST = "results/acroforms/field_format.pdf";
    

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FormatFields().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        form.setField("Name", "1.0", "100%");
        form.setField("Company", "1217000.000000", "$1,217,000");
        stamper.close();
    }
}
