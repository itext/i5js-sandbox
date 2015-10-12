/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/30186774/2d-barcode-generation-issue-in-java
 */
package sandbox.barcodes;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class BarcodePlacement {
    public static final String DEST = "results/barcodes/barcode_placement.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BarcodePlacement().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        Image img = createBarcode(cb, "This is a 2D barcode", 1, 1);
        document.add(new Paragraph(String.format("This barcode measures %s by %s user units", img.getScaledWidth(), img.getScaledHeight())));
        document.add(img);
        img = createBarcode(cb, "This is NOT a raster image", 3, 3);
        document.add(new Paragraph(String.format("This barcode measures %s by %s user units", img.getScaledWidth(), img.getScaledHeight())));
        document.add(img);
        img = createBarcode(cb, "This is vector data drawn on a PDF page", 1, 3);
        document.add(new Paragraph(String.format("This barcode measures %s by %s user units", img.getScaledWidth(), img.getScaledHeight())));
        document.add(img);
        document.close();
    }
    
    public Image createBarcode(PdfContentByte cb, String text, float mh, float mw) throws BadElementException {
        BarcodePDF417 pf = new BarcodePDF417();
        pf.setText("BarcodePDF417 barcode");
        Rectangle size = pf.getBarcodeSize();
        PdfTemplate template = cb.createTemplate(mw * size.getWidth(), mh * size.getHeight());
        pf.placeBarcode(template, BaseColor.BLACK, mh, mw);
        return Image.getInstance(template);
    }
}
