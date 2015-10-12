/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27989754/itext-failure-with-adding-elements-with-5-5-4
 */
package sandbox.objects;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ColumnTextPhrase {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/objects/column_text_phrase.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColumnTextPhrase().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte cb = stamper.getOverContent(1);
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(120f, 48f, 200f, 600f);
        Font f = new Font();
        Paragraph pz = new Paragraph(new Phrase(20, "Hello World!", f));
        ct.addElement(pz);
        ct.go();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", BaseFont.EMBEDDED);
        f = new Font(bf, 13);
        ct = new ColumnText(cb);
        ct.setSimpleColumn(120f, 48f, 200f, 700f);
        pz = new Paragraph ("Hello World!", f);
        ct.addElement(pz);
        ct.go();
        stamper.close();
        reader.close();
    }
}
