/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27752409/using-itext-to-draw-separator-line-as-continuous-hypen-in-a-table-row
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class CustomDashedLine {
    
    class CustomDashedLineSeparator extends DottedLineSeparator {
        protected float dash = 5;
        protected float phase = 2.5f;

        public float getDash() {
            return dash;
        }

        public float getPhase() {
            return phase;
        }

        public void setDash(float dash) {
            this.dash = dash;
        }

        public void setPhase(float phase) {
            this.phase = phase;
        }
        
        public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y) {
            canvas.saveState();
            canvas.setLineWidth(lineWidth);
            canvas.setLineDash(dash, gap, phase);
            drawLine(canvas, llx, urx, y);
            canvas.restoreState();
        }
    }
    
    public static final String DEST = "results/objects/custom_dashed_line.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CustomDashedLine().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Before dashed line"));
        CustomDashedLineSeparator separator = new CustomDashedLineSeparator();
        separator.setDash(10);
        separator.setGap(7);
        separator.setLineWidth(3);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
        document.add(new Paragraph("After dashed line"));
        document.close();
    }
}
