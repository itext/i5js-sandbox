/**
 * Example written for JavaOne 2016.
 * Differences between iText 5 and iText 7 are discussed in the JavaOne talk
 * "Oops, I broke my API" by Raf Hens and Bruno Lowagie.
 * This is the iText 5 version of one of the examples.
 */
package javaone.edition16;

import com.itextpdf.text.Chunk;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.WrapToTest;

@WrapToTest
public class HelloWorldInternational {

    public static final String DEST
        = "results/javaone/edition16/hello_international.pdf";

    public static void main(String[] args)
    	throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
    	new HelloWorldInternational().createPdf(DEST);
    }

    public void createPdf(String dest)
	throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font font = new Font(FontFamily.TIMES_ROMAN);
        Font font14pt = new Font(FontFamily.TIMES_ROMAN, 14);
        Font font10pt = new Font(FontFamily.TIMES_ROMAN, 10);
        BaseFont bf_russian = BaseFont.createFont("resources/fonts/FreeSans.ttf", "CP1251", BaseFont.EMBEDDED);
        Font russian = new Font(bf_russian, 12);
        BaseFont bf_cjk = BaseFont.createFont("resources/fonts/NotoSansCJKsc-Regular.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font cjk = new Font(bf_cjk, 12);
        Paragraph p = new Paragraph("Hello World! ", font);
        Chunk chunk = new Chunk("Hallo Wereld! ", font14pt);
        p.add(chunk);
        chunk = new Chunk("Bonjour le monde! ", font10pt);
        chunk.setTextRise(4);
        p.add(chunk);
        chunk = new Chunk(
            "\u0417\u0434\u0440\u0430\u0432\u0441\u0442\u0432\u0443\u043b\u0442\u0435 \u043c\u0438\u0440! ", russian);
        p.add(chunk);
        p.add(new Chunk("\u4f60\u597d\u4e16\u754c! ", cjk));
        p.add(new Chunk("\uc5ec\ubcf4\uc138\uc694 \uc138\uacc4!", cjk));
        document.add(p);
        document.close();
    }
}
