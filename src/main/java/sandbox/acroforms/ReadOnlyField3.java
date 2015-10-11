/*
 * Example written by Bruno Lowagie.
 */
package sandbox.acroforms;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ReadOnlyField3 {
    
    public static final String DEST = "results/acroforms/multilineField3.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ReadOnlyField3().createPdf(DEST);
    }
        
    public void createPdf(String filename) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(createForm());
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filename));
        AcroFields form = stamper.getAcroFields();
        form.setGenerateAppearances(false);
        form.setFieldProperty("text1", "setfflags", TextField.READ_ONLY, null);
        form.setField("text1", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        form.setFieldProperty("text2", "setfflags", TextField.READ_ONLY, null);
        form.setField("text2", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        form.setFieldProperty("text3", "setfflags", TextField.READ_ONLY, null);
        form.setField("text3", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        form.setFieldProperty("text4", "setfflags", TextField.READ_ONLY, null);
        form.setField("text4", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        stamper.close();
    }
    
    public byte[] createForm() throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        Rectangle rect = new Rectangle(36, 770, 144, 806);
        writer.addAnnotation(getTextField(writer, rect, "text1"));
        rect = new Rectangle(148, 770, 256, 806);
        writer.addAnnotation(getTextField(writer, rect, "text2"));
        rect = new Rectangle(36, 724, 144, 760);
        writer.addAnnotation(getTextField(writer, rect, "text3"));
        rect = new Rectangle(148, 727, 256, 760);
        writer.addAnnotation(getTextField(writer, rect, "text4"));
        document.close();
        return baos.toByteArray();
    }

    public PdfFormField getTextField(PdfWriter writer, Rectangle rect, String name) throws IOException, DocumentException {
        TextField tf = new TextField(writer, rect, name);
        tf.setFontSize(16);
        tf.setOptions(TextField.MULTILINE);
        return tf.getTextField();
    }
}
