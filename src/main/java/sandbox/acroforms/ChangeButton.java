/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/26804092/itext-button-resize-affects-label
 * It will only work starting with iText 5.5.4
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ChangeButton {

    public static final String SRC = "resources/pdfs/hello_button.pdf";
    public static final String DEST = "results/acroforms/hello_button_changed.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ChangeButton().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        PushbuttonField button = form.getNewPushbuttonFromField("Test");
        Rectangle box = button.getBox();
        box.setRight(box.getRight() + 72);  // Increase width by 1"
        button.setBox(box);
        button.setText("Print Amended");
        form.replacePushbuttonField("Test", button.getField());
        stamper.close();
    }
}
