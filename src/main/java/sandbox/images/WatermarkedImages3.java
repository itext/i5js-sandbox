/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/28515474/how-to-add-text-on-the-last-page-through-pdfcontentbyte
 */
package sandbox.images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class WatermarkedImages3 {
    public static final String IMAGE1 = "resources/images/bruno.jpg";
    public static final Font FONT = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.GRAYWHITE);
    public static final String DEST = "results/images/watermark3.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new WatermarkedImages3().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        for (int i = 0; i < 50; i++) {
            table.addCell("rahlrokks doesn't listen to what people tell him");
        }
        PdfContentByte cb = writer.getDirectContentUnder();
        table.addCell(getWatermarkedImage(cb, Image.getInstance(IMAGE1), "Bruno"));
        document.add(table);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("Bruno knows best"), 260, 400, 45);
        document.close();
    }
    
    public Image getWatermarkedImage(PdfContentByte cb, Image img, String watermark) throws DocumentException {
        float width = img.getScaledWidth();
        float height = img.getScaledHeight();
        PdfTemplate template = cb.createTemplate(width, height);
        template.addImage(img, width, 0, 0, height, 0, 0);
        ColumnText.showTextAligned(template, Element.ALIGN_CENTER,
                new Phrase(watermark, FONT), width / 2, height / 2, 30);
        return Image.getInstance(template);
    }
}
