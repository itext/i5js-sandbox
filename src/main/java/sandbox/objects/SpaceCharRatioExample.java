/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/30869268/java-itext-alignment
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class SpaceCharRatioExample {
    
    public static final String DEST = "results/objects/space_char_ratio.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SpaceCharRatioExample().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setIndentationLeft(20);
        paragraph.setIndentationRight(20);
        paragraph.add("HelloWorld HelloWorld HelloWorld HelloWorld HelloWorld"+ 
            "HelloWorld HelloWorldHelloWorldHelloWorldHelloWorld"+
            "HelloWorld HelloWorld HelloWorld HelloWorldHelloWorldHelloWorld");
        document.add(paragraph);
        // step 5
        document.close();
    }
}
