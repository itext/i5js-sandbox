/*
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/30233783/alignment-issue-with-multiline-text-form-fields-in-itextsharp
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
public class MultiLineField {
    
    public static final String DEST = "results/acroforms/multinewline.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultiLineField().createPdf(DEST);
    }
        
    public void createPdf(String filename) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(createForm());
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filename));
        AcroFields form = stamper.getAcroFields();
        form.setField("text", "A B C D E F\nG H I J K L M N\nO P Q R S T U\r\nV W X Y Z\n\nAlphabet street");
        stamper.setFormFlattening(true);
        stamper.close();
    }
    
    public byte[] createForm() throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        Rectangle rect = new Rectangle(36, 720, 144, 806);
        TextField tf = new TextField(writer, rect, "text");
        tf.setOptions(TextField.MULTILINE);
        writer.addAnnotation(tf.getTextField());
        document.close();
        return baos.toByteArray();
    }
}
