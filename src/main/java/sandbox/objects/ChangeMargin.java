/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30328489/itextsharp-change-margins-no-document-setpagesize
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class ChangeMargin {
    
    public static final String DEST = "results/objects/change_margin.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ChangeMargin().createPdf(DEST);
    }
    
    
    
    public void createPdf(String dest) throws IOException, DocumentException {
        float left = 30;
        float right = 30;
        float top = 60;
        float bottom = 0;
        Document document = new Document(PageSize.A4, left, right, top, bottom);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.setMargins(left, right, 0, bottom);
        for (int i = 0; i < 60; i++) {
            document.add(new Paragraph("This is a test"));
        }
        document.close();
    }
}
