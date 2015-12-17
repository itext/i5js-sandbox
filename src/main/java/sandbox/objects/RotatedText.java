/*
 * This example was written in answer to:
 * http://stackoverflow.com/questions/34310018/changing-font-on-pdf-rotated-text
 */
package sandbox.objects;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class RotatedText {
    
    public static final String DEST = "results/objects/rotated_text.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RotatedText().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document(new Rectangle(60, 120), 5, 5, 5, 5);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte canvas = writer.getDirectContent();
        
        Font big_bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        Font small_bold = new Font(FontFamily.HELVETICA, 6, Font.BOLD);
        Font regular = new Font(FontFamily.HELVETICA, 6);
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk("23", big_bold));
        p1.add(new Chunk("000", small_bold));
        document.add(p1);
        
        Paragraph p2 = new Paragraph("T.T.C.", regular);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        
        BarcodeEAN barcode = new BarcodeEAN();
        barcode.setCodeType(Barcode.EAN8);
        barcode.setCode("12345678");
        Rectangle rect = barcode.getBarcodeSize();
        PdfTemplate template = canvas.createTemplate(rect.getWidth(), rect.getHeight() + 10);
        ColumnText.showTextAligned(template, Element.ALIGN_LEFT,
                new Phrase("DARK GRAY", regular), 0, rect.getHeight() + 2, 0);
        barcode.placeBarcode(template, BaseColor.BLACK, BaseColor.BLACK);
        Image image = Image.getInstance(template);
        image.setRotationDegrees(90);
        document.add(image);
        
        Paragraph p3 = new Paragraph("SMALL", regular);
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);
        
        // step 5
        document.close();
    }
}
