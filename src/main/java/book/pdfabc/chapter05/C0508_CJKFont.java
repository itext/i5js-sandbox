/**
 * This is an example was written by Bruno Lowagie.
 * It is used in the book "The ABC of PDF".
 * This book can be downloaded here: https://leanpub.com/itext_pdfabc/
 */
package book.pdfabc.chapter05;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class C0508_CJKFont {

    /** The resulting PDF file. */
    public static final String DEST = "results/pdfabc/chapter05/cjk_font.pdf";
    
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException 
     * @throws IOException 
     * @throws    DocumentException 
     * @throws    IOException
     */
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bf, 12);
        document.add(new Paragraph(bf.getPostscriptFontName(), font));
        document.add(new Paragraph("House of The Flying Daggers (China), by Zhang Yimou", font));
        document.add(new Paragraph("\u5341\u950a\u57cb\u4f0f", font));
        bf = BaseFont.createFont("KozMinPro-Regular", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
        font = new Font(bf, 12);
        document.add(new Paragraph(bf.getPostscriptFontName(), font));
        document.add(new Paragraph("Nobody Knows (Japan), by Hirokazu Koreeda", font));
        document.add(new Paragraph("\u8ab0\u3082\u77e5\u3089\u306a\u3044", font));
        bf = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
        font = new Font(bf, 12);
        document.add(new Paragraph(bf.getPostscriptFontName(), font));
        document.add(new Paragraph("'3-Iron' aka 'Bin-jip' (South-Korea), by Kim Ki-Duk", font));
        document.add(new Paragraph("\ube48\uc9d1", font));

        // step 5
        document.close();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C0508_CJKFont().createPdf(DEST);
    }
}
