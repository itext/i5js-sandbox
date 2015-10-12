/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28418555/remove-page-reference-from-annotation0
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddFieldAndKids {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/stamper/hello_with_field_kids.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddFieldAndKids().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfWriter writer = stamper.getWriter();
        PdfFormField personal = PdfFormField.createEmpty(writer);
        personal.setFieldName("personal");
        TextField name = new TextField(writer, new Rectangle(36, 760, 144, 790), "name");
        PdfFormField personal_name = name.getTextField();
        personal.addKid(personal_name);
        TextField password = new TextField(writer, new Rectangle(150, 760, 450, 790), "password");
        PdfFormField personal_password = password.getTextField();
        personal.addKid(personal_password);
        stamper.addAnnotation(personal, 1);
        stamper.close();
        reader.close();
    }
}
