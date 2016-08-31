/**
 * Example written for JavaOne 2016.
 * Differences between iText 5 and iText 7 are discussed in the JavaOne talk
 * "Oops, I broke my API" by Raf Hens and Bruno Lowagie.
 * This is the iText 5 version of one of the examples.
 */
package javaone.edition16;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sandbox.WrapToTest;

@WrapToTest
public class HelloWorld {

    public static final String DEST
        = "results/javaone/edition16/hello.pdf";

    public static void main(String[] args)
    	throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
    	new HelloWorld().createPdf(DEST);
    }

    public void createPdf(String dest)
	    throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(
            document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Hello World!"));
        document.close();
    }
}
