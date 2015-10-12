/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28487918/how-to-make-it-start-from-the-same-point
 */
package sandbox.objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class IndentationOptions {

    public static final String DEST = "results/objects/indentation_options.pdf";
    public static final String LABEL = "A list of stuff: ";
    public static final String CONTENT = "test A, test B, coconut, coconut, watermelons, apple, oranges, many more " +
        "fruites, carshow, monstertrucks thing, everything is startting on the " +
        "same point in the line now";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new IndentationOptions().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        List list = new List();
        list.setListSymbol(new Chunk(LABEL));
        list.add(CONTENT);
        document.add(list);
        document.add(Chunk.NEWLINE);
        
        BaseFont bf = BaseFont.createFont();
        Paragraph p = new Paragraph(LABEL + CONTENT, new Font(bf, 12));
        float indentation = bf.getWidthPoint(LABEL, 12);
        p.setIndentationLeft(indentation);
        p.setFirstLineIndent(-indentation);
        document.add(p);
        document.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setTotalWidth(new float[]{indentation + 4, 519 - indentation});
        table.setLockedWidth(true);
        table.addCell(LABEL);
        table.addCell(CONTENT);
        document.add(table);
        
        document.close();
    }
}
