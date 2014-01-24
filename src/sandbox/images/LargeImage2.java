package sandbox.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PRIndirectReference;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class LargeImage2 {

    public static void main(String[] args) throws DocumentException, IOException {
        PdfReader reader = new PdfReader("resources/pdfs/large_image.pdf");
        // The width and the height of a PDF page may not exceed 14400 user units:
        Rectangle rect = reader.getPageSize(1);
        if (rect.getWidth() < 14400 && rect.getHeight() < 14400) {
            System.out.println("The size of the PDF document is withing the accepted limits");
            System.exit(0);
        }
        // We assume that there's a single large picture on the first page
        PdfDictionary page = reader.getPageN(1);
        PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
        PdfDictionary xobjects = resources.getAsDict(PdfName.XOBJECT);
        PdfName imgName = xobjects.getKeys().iterator().next();
        Image img = Image.getInstance((PRIndirectReference)xobjects.getAsIndirectObject(imgName));
        img.scaleToFit(14400, 14400);
        img.setAbsolutePosition(0, 0);
        // We create a temporary file that will reuse the image
        File tmp = File.createTempFile("large_image", ".pdf", new File("results"));
        tmp.deleteOnExit();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(tmp));
        // We create a new first page and add the image
        stamper.insertPage(1, new Rectangle(img.getScaledWidth(), img.getScaledHeight()));
        stamper.getOverContent(1).addImage(img);
        stamper.close();
        reader.close();
        // We create a new file that only contains the new first page
        reader = new PdfReader(tmp.getAbsolutePath());
        reader.selectPages("1");
        stamper = new PdfStamper(reader, new FileOutputStream("results/large_image2.pdf"));
        stamper.close();
        reader.close();
    }
}
