/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30508966/saving-xfdf-as-pdf
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.XfdfReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author iText
 */
public class ImportXFDF {

    public static final String SRC = "resources/pdfs/Requisition_Fillable.pdf";
    public static final String XFDF = "resources/pdfs/data.xfdf";
    public static final String DEST = "results/acroforms/xfdf_merged.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImportXFDF().fillXfdf(SRC, XFDF, DEST);
    }

    protected void fillXfdf(String SRC, String XFDF, String DEST) throws IOException, DocumentException {
        // We receive the XML bytes
        XfdfReader xfdf = new XfdfReader(new FileInputStream(XFDF));
        // We get the corresponding form
        PdfReader reader = new PdfReader(SRC);
        // We create an OutputStream for the new PDF
        FileOutputStream baos = new FileOutputStream(DEST);
        // Now we create the PDF
        PdfStamper stamper = new PdfStamper(reader, baos);
        // We alter the fields of the existing PDF
        AcroFields fields = stamper.getAcroFields();
        fields.setFields(xfdf);
        // take away all interactivity
        stamper.setFormFlattening(true);
        // close the stamper
        stamper.close();
        reader.close();
    }
}
