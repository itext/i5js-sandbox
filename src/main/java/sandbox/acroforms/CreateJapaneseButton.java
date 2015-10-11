/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29016333/pushbuttonfield-with-double-byte-character
 */
package sandbox.acroforms;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class CreateJapaneseButton {
    
    public static final String DEST = "results/acroforms/japanese_button.pdf";
    public static final String JAPANESE = "\u3042\u304d\u3089";
    public static final String FONT = "resources/fonts/FreeSans.ttf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CreateJapaneseButton().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PushbuttonField button = new PushbuttonField(writer, new Rectangle(36, 780, 144, 806), "japanese");
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        button.setFont(bf);
        button.setText(JAPANESE);
        writer.addAnnotation(button.getField());
        document.close();
    }

}
