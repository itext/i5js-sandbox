/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29275194/change-pdf-fillcolor-annotation-property-using-itextsharp-c-sharp
 */
package sandbox.annotations;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfBorderArray;
import com.itextpdf.text.pdf.PdfDashPattern;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class CircleAnnotation {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/annotations/hello_circle.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CircleAnnotation().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Rectangle rect = new Rectangle(150, 770, 200, 820);
        PdfAnnotation annotation = PdfAnnotation.createSquareCircle(
            stamper.getWriter(), rect, "Circle", false);
        annotation.setTitle("Circle");
        annotation.setColor(BaseColor.BLUE);
        annotation.setFlags(PdfAnnotation.FLAGS_PRINT);
        annotation.setBorder(new PdfBorderArray(0, 0, 2, new PdfDashPattern()));
        annotation.put(PdfName.IC, new PdfArray(new int[]{1, 0, 0}));
        stamper.addAnnotation(annotation, 1);
        stamper.close();
    }
}
