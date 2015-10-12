/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/28554413/how-to-add-overlay-text-with-link-annotations-to-existing-pdf
 */
package sandbox.annotations;

import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBorderArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddLinkAnnotation4 {

    public class AddAnnotation extends PdfPageEventHelper {
        protected PdfStamper stamper;
        protected AffineTransform transform;
        
        public AddAnnotation(PdfStamper stamper, AffineTransform transform) {
            this.stamper = stamper;
            this.transform = transform;
        }
        
        @Override
        public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
            float[] pts = {rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop()};
            transform.transform(pts, 0, pts, 0, 2);
            float[] dstPts = {pts[0], pts[1], pts[2], pts[3]};
            rect = new Rectangle(dstPts[0], dstPts[1], dstPts[2], dstPts[3]);
            PdfAnnotation annot = PdfAnnotation.createLink(writer, rect, PdfAnnotation.HIGHLIGHT_INVERT, new PdfAction(text));
            annot.setBorder(new PdfBorderArray(0, 0, 0));
            stamper.addAnnotation(annot, 1);
        }
        
    }
    
    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/annotations/link_annotation4.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddLinkAnnotation4().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AffineTransform transform = AffineTransform.getRotateInstance(Math.PI / 2);
        stamper.getWriter().setPageEvent(new AddAnnotation(stamper, transform));
        PdfContentByte canvas = stamper.getOverContent(1);
        Font bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        Chunk chunk = new Chunk("The Best iText Questions on StackOverflow", bold);
        chunk.setGenericTag("http://pages.itextpdf.com/ebook-stackoverflow-questions.html");
        Phrase p = new Phrase("Download ");
        p.add(chunk);
        p.add(" and discover more than 200 questions and answers.");
        canvas.saveState();
        canvas.concatCTM(transform);
        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(36, -559, 806, -36);
        ct.addText(p);
        ct.go();
        canvas.restoreState();
        stamper.close();
        reader.close();
    }
}
