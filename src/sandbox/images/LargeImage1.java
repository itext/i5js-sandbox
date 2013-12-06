package sandbox.images;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfImageObject;

public class LargeImage1 {

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
        PdfName imgRef = xobjects.getKeys().iterator().next();
        PRStream imgStream = (PRStream) xobjects.getAsStream(imgRef);
        // We now create a new Image object based on the bytes in the stream
        PdfImageObject imgObject = new PdfImageObject(imgStream);
        Image img = Image.getInstance(imgObject.getImageAsBytes());
        img.scaleToFit(14400, 14400);
        img.setAbsolutePosition(0, 0);
        // We create a new document with the correct size
        Document document = new Document(new Rectangle(img.getScaledWidth(), img.getScaledHeight()));
        PdfWriter.getInstance(document, new FileOutputStream("results/large_image1.pdf"));
        document.open();
        document.add(img);
        document.close();
    }
}
