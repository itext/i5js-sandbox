/*
 * This example was written in answer to the following question:
 * http://stackoverflow.com/questions/34036200
 */
package sandbox.pdfa;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddAltTags {
    
    public static final String SRC = "resources/pdfs/no_alt_attribute.pdf";
    public static final String DEST = "results/pdfa/added_alt_attributes.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddAltTags().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary structTreeRoot = catalog.getAsDict(PdfName.STRUCTTREEROOT);
        manipulate(structTreeRoot);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
    }
    
    public void manipulate(PdfDictionary element) {
        if (element == null)
            return;
        if (PdfName.FIGURE.equals(element.get(PdfName.S))) {
            element.put(PdfName.ALT, new PdfString("Figure without an Alt description"));
        }
        PdfArray kids = element.getAsArray(PdfName.K);
        if (kids == null) return;
        for (int i = 0; i < kids.size(); i++)
            manipulate(kids.getAsDict(i));
    }
}
