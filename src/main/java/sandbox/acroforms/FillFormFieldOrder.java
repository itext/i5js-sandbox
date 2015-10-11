/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29633035/change-acrofields-order-in-existing-pdf-with-itext
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class FillFormFieldOrder {
    
    public static final String SRC = "resources/pdfs/calendar_example.pdf";
    public static final String DEST = "results/acroforms/calendar_filled.pdf";
    
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillFormFieldOrder().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        go2(go1(src), dest);
    }
    
    public byte[] go1(String src) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);
        AcroFields form = stamper.getAcroFields();
        form.setField("sunday_1", "1");
        form.setField("sunday_2", "2");
        form.setField("sunday_3", "3");
        form.setField("sunday_4", "4");
        form.setField("sunday_5", "5");
        form.setField("sunday_6", "6");
        stamper.setFormFlattening(true);
        stamper.partialFormFlattening("sunday_1");
        stamper.partialFormFlattening("sunday_2");
        stamper.partialFormFlattening("sunday_3");
        stamper.partialFormFlattening("sunday_4");
        stamper.partialFormFlattening("sunday_5");
        stamper.partialFormFlattening("sunday_6");
        stamper.close();
        reader.close();
        return baos.toByteArray();
    }
    
    public void go2(byte[] src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        form.setField("sunday_1_notes", "It's Sunday today, let's go to the sea");
        form.setField("sunday_2_notes", "It's Sunday today, let's go to the park");
        form.setField("sunday_3_notes", "It's Sunday today, let's go to the beach");
        form.setField("sunday_4_notes", "It's Sunday today, let's go to the woods");
        form.setField("sunday_5_notes", "It's Sunday today, let's go to the lake");
        form.setField("sunday_6_notes", "It's Sunday today, let's go to the river");
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }
}
