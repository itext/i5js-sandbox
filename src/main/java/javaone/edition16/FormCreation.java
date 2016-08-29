package javaone.edition16;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FormCreation {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/javaone/edition16/form_creation.pdf";
    

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FormCreation().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        TextField tf = new TextField(stamper.getWriter(), new Rectangle(110, 780, 180, 806), "text");
        tf.setBorderColor(BaseColor.BLUE);
        tf.setBorderWidth(2);
        tf.setTextColor(BaseColor.RED);
        tf.setText("Text field");
        PdfFormField field = tf.getTextField();
        stamper.addAnnotation(field, 1);
        stamper.close();
        reader.close();
    }
}
