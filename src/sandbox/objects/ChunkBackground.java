/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19976343/how-to-set-the-paragraph-of-itext-pdf-file-as-rectangle-with-background-color-in
 * 
 * We create a Chunk and add a background color.
 */
package sandbox.objects;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.SandboxTest;

public class ChunkBackground extends SandboxTest {

    @Override
    protected String getOutPdf() {
        return "./results/objects/chunk_background.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/objects/cmp_chunk_background.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        Font f = new Font(FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.WHITE);
        Chunk c = new Chunk("White text on red background", f);
        c.setBackground(BaseColor.RED);
        Paragraph p = new Paragraph(c);
        document.add(p);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new ChunkBackground();
        test.makePdf();
    }
}
