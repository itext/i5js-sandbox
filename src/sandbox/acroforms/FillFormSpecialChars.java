/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20401125/overlapping-characters-in-text-field-itext-pdf
 * 
 * Sometimes you need to change the font of a field.
 */
package sandbox.acroforms;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class FillFormSpecialChars {

    public static void main(String[] args) throws IOException, DocumentException, IOException {
        PdfReader reader = new PdfReader("resources/pdfs/test.pdf");
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("results/test.pdf"));
        AcroFields fields = stamper.getAcroFields();
        fields.setGenerateAppearances(true);
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fields.setFieldProperty("test", "textfont", bf, null);
        fields.setField("test", "\u011b\u0161\u010d\u0159\u017e\u00fd\u00e1\u00ed\u00e9");
        fields.setFieldProperty("test2", "textfont", bf, null);
        fields.setField("test2", "\u011b\u0161\u010d\u0159\u017e\u00fd\u00e1\u00ed\u00e9");
        stamper.close();
    }
}
