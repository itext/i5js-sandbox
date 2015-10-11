/*
 * Example written by Bruno Lowagie.
 */
package sandbox.acroforms;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
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
public class ReadOnlyField {
    
    public static final String DEST = "results/acroforms/multilineField.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ReadOnlyField().createPdf(DEST);
    }
        
    public void createPdf(String filename) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(createForm());
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filename));
        AcroFields form = stamper.getAcroFields();
        form.setFieldProperty("text", "setfflags", TextField.READ_ONLY, null);
        form.setField("text", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        stamper.close();
    }
    
    public byte[] createForm() throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        Rectangle rect = new Rectangle(36, 770, 144, 806);
        TextField tf = new TextField(writer, rect, "text");
        tf.setFontSize(20);
        tf.setOptions(TextField.MULTILINE);
        writer.addAnnotation(tf.getTextField());
        document.close();
        return baos.toByteArray();
    }
}
