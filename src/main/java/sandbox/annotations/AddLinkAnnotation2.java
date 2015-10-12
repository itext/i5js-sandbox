/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/28554413/how-to-add-overlay-text-with-link-annotations-to-existing-pdf
 */
package sandbox.annotations;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddLinkAnnotation2 {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/annotations/link_annotation2.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddLinkAnnotation2().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte canvas = stamper.getOverContent(1);
        Font bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        Chunk chunk = new Chunk("The Best iText Questions on StackOverflow", bold);
        chunk.setAnchor("http://pages.itextpdf.com/ebook-stackoverflow-questions.html");
        Phrase p = new Phrase("Download ");
        p.add(chunk);
        p.add(" and discover more than 200 questions and answers.");
        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(36, 700, 559, 750);
        ct.addText(p);
        ct.go();
        stamper.close();
        reader.close();
    }
}
