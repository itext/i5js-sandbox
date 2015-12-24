/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34445641
 */
package sandbox.columntext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class SimpleColumn {
    public static final String DEST = "results/columntext/simple_column.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleColumn().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Rectangle rect = new Rectangle(100, 120);
        Document document = new Document(rect);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte canvas = writer.getDirectContent();
        BaseFont baseFont = BaseFont.createFont();
        ColumnText ct = new ColumnText(canvas);
        Font paragraphFont=new Font(baseFont,4.5f);
        ct.setSimpleColumn(9, 70, 70, 95);
        Paragraph paragraph = new Paragraph("REALLLLLLLLLLY LONGGGGGGGGGG text",paragraphFont);
        ct.addElement(paragraph);
        ct.go();
        // step 5
        document.close();
    }
}
