/**
 * Example written by Bruno Lowagie in answer to:
 * http://thread.gmane.org/gmane.comp.java.lib.itext.general/65892
 * 
 * Some text displayed using a Small Caps font.
 */
package sandbox.fonts;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.SandboxTest;

public class SmallCapsExample extends SandboxTest {

    @Override
    protected String getOutPdf() {
        return "./results/fonts/small_caps.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/fonts/cmp_small_caps.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        BaseFont bf = BaseFont.createFont("./resources/fonts/Delicious-SmallCaps.otf",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 12);
        Paragraph p = new Paragraph("This is some text displayed using a Small Caps font.", f);
        document.add(p);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new SmallCapsExample();
        test.makePdf();
    }
}
