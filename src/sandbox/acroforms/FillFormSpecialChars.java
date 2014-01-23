/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20401125/overlapping-characters-in-text-field-itext-pdf
 * 
 * Sometimes you need to change the font of a field.
 */
package sandbox.acroforms;

import java.io.FileOutputStream;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.junit.Assert;
import sandbox.SandboxTest;

public class FillFormSpecialChars extends SandboxTest {

    String value = "\u011b\u0161\u010d\u0159\u017e\u00fd\u00e1\u00ed\u00e9";
    String inputPdf = "./resources/pdfs/test.pdf";

    @Override
    protected String getOutPdf() {
        return "./results/acroforms/test.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/acroforms/cmp_test.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        PdfReader reader = new PdfReader(inputPdf);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(outPdf));
        AcroFields fields = stamper.getAcroFields();
        fields.setGenerateAppearances(true);
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fields.setFieldProperty("test", "textfont", bf, null);
        fields.setField("test", value);
        fields.setFieldProperty("test2", "textfont", bf, null);
        fields.setField("test2", value);
        stamper.close();
    }

    @Override
    protected void assertPdf(String outPdf) throws Exception {
        PdfReader reader = new PdfReader(outPdf);
        AcroFields fields = reader.getAcroFields();
        Assert.assertTrue("Check test value", fields.getField("test").equals(value));
        Assert.assertTrue("Check test2 value", fields.getField("test2").equals(value));
        reader.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new FillFormSpecialChars();
        test.makePdf();
    }
}
