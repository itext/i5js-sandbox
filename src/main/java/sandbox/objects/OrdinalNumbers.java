/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/30322665/how-to-access-the-sup-tag-in-itextsharp
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class OrdinalNumbers {
    public static final String DEST = "results/objects/ordinal_numbers.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new OrdinalNumbers().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font small = new Font(FontFamily.HELVETICA, 6);
        Chunk st = new Chunk("st", small);
        st.setTextRise(7);
        Chunk nd = new Chunk("nd", small);
        nd.setTextRise(7);
        Chunk rd = new Chunk("rd", small);
        rd.setTextRise(7);
        Chunk th = new Chunk("th", small);
        th.setTextRise(7);
        Paragraph first = new Paragraph();
        first.add("The 1");
        first.add(st);
        first.add(" of May");
        document.add(first);
        Paragraph second = new Paragraph();
        second.add("The 2");
        second.add(nd);
        second.add(" and the 3");
        second.add(rd);
        second.add(" of June");
        document.add(second);
        Paragraph fourth = new Paragraph();
        fourth.add("The 4");
        fourth.add(rd);
        fourth.add(" of July");
        document.add(fourth);
        document.close();
    }
}
