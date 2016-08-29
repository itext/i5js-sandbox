/**
 * Example written for JavaOne 2016.
 * Differences between iText 5 and iText 7 are discussed in the JavaOne talk
 * "Oops, I broke my API" by Raf Hens and Bruno Lowagie.
 * This is the iText 5 version of one of the examples.
 */
package javaone.edition16;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class Text2PdfPageEvents {

    class MyPageEvents extends PdfPageEventHelper {

        protected float startpos = -1;
        protected boolean title = true;

        public void setTitle(boolean title) {
            this.title = title;
        }
        
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            Rectangle pagesize = document.getPageSize();
            ColumnText.showTextAligned(
                writer.getDirectContent(),
                Element.ALIGN_CENTER,
                new Phrase(String.valueOf(writer.getPageNumber())),
                (pagesize.getLeft() + pagesize.getRight()) / 2,
                pagesize.getBottom() + 15,
                0);
            if (startpos != -1)
                onParagraphEnd(writer, document, pagesize.getBottom(document.bottomMargin()));
            startpos = pagesize.getTop(document.topMargin());
        }

        @Override
        public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
            startpos = paragraphPosition;
        }
        
        @Override
        public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
            if (!title) return;
            PdfContentByte canvas = writer.getDirectContentUnder();
            Rectangle pagesize = document.getPageSize();
            canvas.saveState();
            canvas.setColorStroke(BaseColor.BLUE);
            canvas.rectangle(
                pagesize.getLeft(document.leftMargin()),
                paragraphPosition - 3,
                pagesize.getWidth() - document.leftMargin() - document.rightMargin(),
                startpos - paragraphPosition);
            canvas.stroke();
            canvas.restoreState();
        }

        
    }
    
    public static final String SRC
        = "resources/text/jekyll_hyde.txt";
    public static final String DEST
        = "results/javaone/edition16/text2pdf_page_events.pdf";

    public static void main(String[] args)
    	throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
    	new Text2PdfPageEvents().createPdf(DEST);
    }
    
    public void createPdf(String dest)
	throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        MyPageEvents events = new MyPageEvents();
        writer.setPageEvent(events);
        document.open();
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        Paragraph p;
        Font normal = new Font(FontFamily.TIMES_ROMAN, 12);
        Font bold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        boolean title = true;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line, title ? bold : normal);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            events.setTitle(title);
            document.add(p);
            title = line.isEmpty();
        }
        document.close();
    }
}
