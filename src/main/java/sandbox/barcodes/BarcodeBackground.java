/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/35454039
 */
package sandbox.barcodes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class BarcodeBackground {
    public static final String DEST = "results/barcodes/barcode_background.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BarcodeBackground().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        Barcode128 code128 = new Barcode128();
        code128.setCode("12345XX789XXX");
        code128.setCodeType(Barcode128.CODE128);
        PdfTemplate template = code128.createTemplateWithBarcode(
                canvas, BaseColor.BLACK, BaseColor.BLACK);
        float x = 36;
        float y = 750;
        float w = template.getWidth();
        float h = template.getHeight();
        canvas.saveState();
        canvas.setColorFill(BaseColor.LIGHT_GRAY);
        canvas.rectangle(x, y, w, h);
        canvas.fill();
        canvas.restoreState();
        canvas.addTemplate(template, 36, 750);
        document.close();
    }
}
