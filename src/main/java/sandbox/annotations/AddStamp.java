/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/29229629/how-to-add-a-printable-or-non-printable-bitmap-stamp-to-a-pdf
 */
package sandbox.annotations;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddStamp {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/annotations/stamp_annotation.pdf";
    public static final String IMG = "resources/images/itext.png";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddStamp().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Image img = Image.getInstance(IMG);
        float w = img.getScaledWidth();
        float h = img.getScaledHeight();
        Rectangle location = new Rectangle(36, 770 - h, 36 + w, 770);
        PdfAnnotation stamp = PdfAnnotation.createStamp(
                stamper.getWriter(), location, null, "ITEXT");                     
        img.setAbsolutePosition(0, 0);
        PdfContentByte cb = stamper.getOverContent(1);
        PdfAppearance app = cb.createAppearance(w, h);
        app.addImage(img);
        stamp.setAppearance(PdfName.N, app);
        stamp.setFlags(PdfAnnotation.FLAGS_PRINT);
        stamper.addAnnotation(stamp, 1);
        stamper.close();
        reader.close();
    }
}
