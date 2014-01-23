/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20119709/itext-hyphen-in-table-cell
 * 
 * We create a table with and we add a word that needs to be hyphenated.
 */
package sandbox.tables;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.HyphenationAuto;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.hyphenation.Hyphenation;
import com.itextpdf.text.pdf.hyphenation.Hyphenator;
import sandbox.SandboxTest;

public class HyphenationExample extends SandboxTest {

    @Override
    protected String getOutPdf() {
        return "./results/tables/hyphenation_table.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/tables/cmp_hyphenation_table.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        Hyphenator h = new Hyphenator("de", "DE", 2, 2);
        Hyphenation s = h.hyphenate("Leistungsscheinziffer");
        System.out.println(s);

        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(10);
        Chunk chunk = new Chunk("Leistungsscheinziffer");
        chunk.setHyphenation(new HyphenationAuto("de", "DE", 2,2));
        table.addCell(new Phrase(chunk));
        Phrase phrase = new Phrase();
        phrase.setHyphenation(new HyphenationAuto("de", "DE", 2,2));
        phrase.add(new Chunk("Leistungsscheinziffer"));
        table.addCell(phrase);
        document.add(table);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new HyphenationExample();
        test.makePdf();
    }
}
