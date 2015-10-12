/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/29331838/add-multiple-images-into-a-single-pdf-file-with-itext-using-java
 */
package sandbox.images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class MultipleImages {
    public static final String[] IMAGES = {
        "resources/images/berlin2013.jpg",
        "resources/images/javaone2013.jpg",
        "resources/images/map_cic.png"
    };
    public static final String DEST = "results/images/multiple_images.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultipleImages().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Image img = Image.getInstance(IMAGES[0]);
        Document document = new Document(img);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        for (String image : IMAGES) {
            img = Image.getInstance(image);
            document.setPageSize(img);
            document.newPage();
            img.setAbsolutePosition(0, 0);
            document.add(img);
        }
        document.close();
    }
}
