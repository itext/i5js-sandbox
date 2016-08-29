package javaone.edition16;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        fields.setField("text", "Field Text");
        stamper.close();
        reader.close();
    }
}
