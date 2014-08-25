/*
 * This code sample was written in the context of
 *
 * JavaOne 2014: PDF is dead. Long live PDF... and Java!
 * Tutorial Session by Bruno Lowagie and Raf Hens
 *
 * Copyright 2014, iText Group NV
 */
package javaone.edition14.part2;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Creates a Tagged PDF with a title that is a header and some text.
 */
public class ThisIsTheTitle {
    /** The resulting PDF */
    public static final String DEST = "results/javaone/edition2014/part2/this_is_the_title.pdf";

    /**
     * Creates a Tagged PDF (with structure).
     * @param args no arguments needed
     * @throws IOException
     * @throws DocumentException 
     */
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ThisIsTheTitle().createPdf(DEST);
    }
    
    /**
     * Creates a Tagged PDF (with structure).
     * @param dest  the path to the resulting PDF document
     * @throws IOException
     * @throws DocumentException 
     */ 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setTagged();
        document.open();
        Font f = new Font(FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD);
        Paragraph p = new Paragraph("This is the title", f);
        p.setRole(PdfName.H1);
        document.add(p);
        document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eleifend, nisi at scelerisque cursus, est augue egestas justo, ac sagittis dolor nulla at ante. Nulla porta, ligula et imperdiet ultrices, erat dolor vehicula libero, eget sollicitudin turpis arcu ut augue. Ut et purus ullamcorper, varius neque et, scelerisque dolor. Pellentesque imperdiet dui at accumsan vehicula. Aenean non leo mi. Ut eu scelerisque ante, scelerisque gravida ligula. Fusce vitae pellentesque odio. Cras sit amet ligula ac sem convallis feugiat quis eu augue. Duis commodo a justo vitae congue. Duis eu bibendum ante, eu semper neque. Vivamus ullamcorper risus at commodo facilisis. Donec id justo vulputate, consectetur ante lobortis, tristique augue. Sed lacinia volutpat congue. Nunc placerat erat condimentum lacinia congue. Mauris ac tellus vel odio suscipit dignissim nec vel lorem. Pellentesque sodales placerat turpis, id fermentum elit sagittis eget."));
        document.close();
    }           
}
