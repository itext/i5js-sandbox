/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/29096314/how-to-make-an-image-a-qualified-mask-candidate-in-itext
 */
package sandbox.images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import sandbox.WrapToTest;

@WrapToTest
public class MakeJpgMask {
    public static final String IMAGE = "resources/images/javaone2013.jpg";
    public static final String MASK = "resources/images/berlin2013.jpg";
    public static final String DEST = "results/images/jpg_mask.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MakeJpgMask().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Image image = Image.getInstance(IMAGE);
        Image mask = makeBlackAndWhitePng(MASK);
        mask.makeMask();
        image.setImageMask(mask);
        image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        document.add(image);
        document.close();
    }
    
    public static Image makeBlackAndWhitePng(String image) throws IOException, DocumentException {
        BufferedImage bi = ImageIO.read(new File(image));
        BufferedImage newBi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
        newBi.getGraphics().drawImage(bi, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(newBi, "png", baos);
        return Image.getInstance(baos.toByteArray());
    }
}
