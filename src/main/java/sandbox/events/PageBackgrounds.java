/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/30211043/change-the-color-of-pdf-pages-alternatively-using-itext-pdf-in-java
 */
package sandbox.events;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sandbox.WrapToTest;

@WrapToTest
public class PageBackgrounds {
    public static final String DEST = "results/events/page_backgrounds.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PageBackgrounds().createPdf(DEST);
    }
    
    public class Background extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            int pagenumber = writer.getPageNumber();
            if (pagenumber % 2 == 1 && pagenumber != 1)
                return;
            PdfContentByte canvas = writer.getDirectContentUnder();
            Rectangle rect = document.getPageSize();
            canvas.setColorFill(pagenumber < 3 ? BaseColor.BLUE : BaseColor.LIGHT_GRAY);
            canvas.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
            canvas.fill();
        }
    }
    
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Background event = new Background();
        writer.setPageEvent(event);
        // step 3
        document.open();
        document.add(new Paragraph("Prime Numbers"));
        document.newPage();
        document.add(new Paragraph("An overview"));
        document.newPage();
        // step 4
        List<Integer> factors;
        for (int i = 2; i < 301; i++) {
            factors = getFactors(i);
            if (factors.size() == 1) {
                document.add(new Paragraph("This is a prime number!"));
            }
            for (int factor : factors) {
                document.add(new Paragraph("Factor: " + factor));
            }
            document.newPage();
        }
        // step 5
        document.close();
    }
    
    public static List<Integer> getFactors(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
          while (n % i == 0) {
            factors.add(i);
            n /= i;
          }
        }
        return factors;
    }
}
