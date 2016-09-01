/**
 * Example written for JavaOne 2016.
 * Differences between iText 5 and iText 7 are discussed in the JavaOne talk
 * "Oops, I broke my API" by Raf Hens and Bruno Lowagie.
 * This is the iText 5 version of one of the examples.
 */
package javaone.edition16;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class FormFilling {

    public static final String SRC = "resources/pdfs/hello_form.pdf";
    public static final String DEST = "results/javaone/edition16/form_filling.pdf";
    

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FormFilling().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields fields = stamper.getAcroFields();
        fields.setFieldProperty("text", "textcolor", BaseColor.BLUE, null);
        fields.setFieldProperty("text", "bordercolor", BaseColor.RED, null);
        fields.setFieldProperty("text", "fontsize", 14, null);
        fields.setField("text", "Field Text");
        stamper.close();
        reader.close();
    }
}
