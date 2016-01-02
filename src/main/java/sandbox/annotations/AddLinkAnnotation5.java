package sandbox.annotations;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBorderArray;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class AddLinkAnnotation5 {

    public static final String SRC = "resources/pdfs/primes.pdf";
    public static final String DEST = "results/annotations/link_annotation5.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddLinkAnnotation5().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        // Here we define the location:
        Rectangle linkLocation = new Rectangle(320, 695, 560, 741);
        // here we add the actual content at this location:
        ColumnText ct = new ColumnText(stamper.getOverContent(1));
        ct.setSimpleColumn(linkLocation);
        ct.addElement(new Paragraph("This is a link. Click it and you'll be forwarded to another page in this document."));
        ct.go();
        // now we create the link that will jump to a specific destination:
        PdfDestination destination = new PdfDestination(PdfDestination.FIT);
        PdfAnnotation link = PdfAnnotation.createLink(stamper.getWriter(),
                linkLocation, PdfAnnotation.HIGHLIGHT_INVERT,
                3, destination);
        // If you don't want a border, here's where you remove it:
        link.setBorder(new PdfBorderArray(0, 0, 0));
        // We add the link (that is the clickable area, not the text!)
        stamper.addAnnotation(link, 1);
        stamper.close();
        reader.close();
    }
}
