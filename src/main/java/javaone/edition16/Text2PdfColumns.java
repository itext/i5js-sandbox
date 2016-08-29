package javaone.edition16;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Text2PdfColumns {

    public static final String SRC
        = "resources/text/jekyll_hyde.txt";
    public static final String DEST
        = "results/javaone/edition16/text2pdf_columns.pdf";

    public static void main(String[] args)
    	throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
    	new Text2PdfColumns().createPdf(DEST);
    }
    
    public void createPdf(String dest)
	throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        ColumnText ct = new ColumnText(writer.getDirectContent());
        Rectangle[] columns = {
            new Rectangle(36, 36, 290, 806),
            new Rectangle(305, 36, 559, 806)
        };
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        Paragraph p;
        Font normal = new Font(FontFamily.TIMES_ROMAN, 12);
        Font bold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        boolean title = true;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line, title ? bold : normal);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            title = line.isEmpty();
            ct.addElement(p);
        }
        int c = 0;
        int status = ColumnText.START_COLUMN;
        while (ColumnText.hasMoreText(status)) {
            ct.setSimpleColumn(columns[c]);
            status = ct.go();
            if (++c == 2) {
                document.newPage();
                c = 0;
            }
        }
        document.close();
    }
}
