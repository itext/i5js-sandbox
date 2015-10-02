/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29303345/java-itext-rotate-a-link-rectangle
 */
package sandbox.annotations;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class AddRotatedAnnotation {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/annotations/rotated_annotation.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddRotatedAnnotation().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfAction action = new PdfAction("http://pages.itextpdf.com/ebook-stackoverflow-questions.html");
        Rectangle linkLocation1 = new Rectangle(30, 770, 120, 800);
        PdfAnnotation link1 = PdfAnnotation.createLink(stamper.getWriter(),
                linkLocation1, PdfAnnotation.HIGHLIGHT_INVERT, action);
        link1.setColor(BaseColor.RED);
        stamper.addAnnotation(link1, 1);
        Rectangle linkLocation2 = new Rectangle(30, 670, 60, 760);
        PdfAnnotation link2 = PdfAnnotation.createLink(stamper.getWriter(),
                linkLocation2, PdfAnnotation.HIGHLIGHT_INVERT, action);
        link2.setColor(BaseColor.GREEN);
        stamper.addAnnotation(link2, 1);
        Rectangle linkLocation3 = new Rectangle(150, 770, 240, 800);
        PdfAnnotation stamp1 = PdfAnnotation.createStamp(stamper.getWriter(), linkLocation3, "Landscape", "Confidential");
        stamper.addAnnotation(stamp1, 1);
        Rectangle linkLocation4 = new Rectangle(150, 670, 240, 760);
        PdfAnnotation stamp2 = PdfAnnotation.createStamp(stamper.getWriter(), linkLocation4, "Portrait", "Confidential");
        stamp2.setRotate(90);
        stamper.addAnnotation(stamp2, 1);
        Rectangle linkLocation5 = new Rectangle(250, 670, 340, 760);
        PdfAnnotation stamp3 = PdfAnnotation.createStamp(stamper.getWriter(), linkLocation5, "Portrait", "Confidential");
        stamp3.setRotate(45);
        stamper.addAnnotation(stamp3, 1);
        stamper.close();
        reader.close();
    }
}
