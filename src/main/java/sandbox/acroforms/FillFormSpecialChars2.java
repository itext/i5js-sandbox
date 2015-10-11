/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/31335715/cannot-show-special-character-in-acro-form-field
 * 
 * Sometimes you need to change the font of a field.
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class FillFormSpecialChars2 {
    
    public static final String SRC = "resources/pdfs/form.pdf";
    public static final String DEST = "results/acroforms/form_special_chars.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillFormSpecialChars2().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(dest));
        AcroFields fields = stamper.getAcroFields();
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, false, null, null, false);
        fields.setFieldProperty("Name", "textfont", bf, null);
        fields.setField("Name", "\u04e711111");
        stamper.setFormFlattening(true);
        stamper.close();
    }
}
