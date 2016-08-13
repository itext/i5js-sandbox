/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/38236515
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class DottedLineEnder {
    public static final String DEST = "results/objects/dotted_line_ender.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DottedLineEnder().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        DottedLineSeparator separator = new DottedLineSeparator();
        Chunk c = new Chunk(separator);
        Paragraph p = new Paragraph("Ends with dots ");
        p.add(c);
        document.add(p);
        p = new Paragraph("This is a much longer paragraph that spans "
                + "several lines. The String used to create this paragraph "
                + "will be split automatically at the end of the line. The "
                + "final line of this paragraph will end in a dotted line. ");
        p.add(c);
        document.add(p);
        document.close();
    }
}
