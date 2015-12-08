/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34117589
 */
package sandbox.stamper;

import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class AddImageAffineTransform {
    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/stamper/hello_with_image.pdf";
    public static final String IMG = "resources/images/bruno.jpg";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddImageAffineTransform().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Image image = Image.getInstance(IMG);
        AffineTransform at = AffineTransform.getTranslateInstance(36, 300);
        at.concatenate(AffineTransform.getScaleInstance(image.getScaledWidth(), image.getScaledHeight()));
        PdfContentByte canvas = stamper.getOverContent(1);
        canvas.addImage(image, at);
        stamper.close();
        reader.close();
    }
    
}
