/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20233630/itextsharp-how-to-add-a-full-line-break
 * 
 * We create a Chunk and add a background color.
 */
package sandbox.objects;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import sandbox.SandboxTest;

public class FullDottedLine extends SandboxTest {

    @Override
    protected String getOutPdf() {
        return "./results/objects/full_dotted_line.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/objects/cmp_full_dotted_line.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outPdf));
            document.open();
            document.add(new Paragraph("Before dotted line"));
            DottedLineSeparator separator = new DottedLineSeparator();
            separator.setPercentage(59500f / 523f);
            Chunk linebreak = new Chunk(separator);
            document.add(linebreak);
            document.add(new Paragraph("After dotted line"));
            document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new FullDottedLine();
        test.makePdf();
    }
}
