/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29037981/how-to-draw-a-rectangle-around-multiline-text
 */
package sandbox.objects;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class DrawRectangleAroundText {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/objects/column_text_rectangle.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DrawRectangleAroundText().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte cb = stamper.getOverContent(1);
        // full column
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(120f, 500f, 250f, 780f);
        Paragraph p = new Paragraph("This is a long paragraph that doesn't"
                + "fit the width we defined for the simple column of the"
                + "ColumnText object, so it will be distributed over several"
                + "lines (and we don't know in advance how many).");
        ct.addElement(p);
        ct.go();
        cb.rectangle(120, 500, 130, 280);
        cb.stroke();
        // used column
        ct = new ColumnText(cb);
        ct.setSimpleColumn(300f, 500f, 430f, 780f);
        ct.addElement(p);
        ct.go();
        float endPos = ct.getYLine() - 5;
        cb.rectangle(300, endPos, 130, 780 - endPos);
        cb.stroke();
        stamper.close();
        reader.close();
    }
}
