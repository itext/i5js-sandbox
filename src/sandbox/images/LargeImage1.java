package sandbox.images;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import sandbox.SandboxTest;

public class LargeImage1 extends SandboxTest {

    String inputPdf = "./resources/pdfs/large_image.pdf";

    @Override
    protected String getOutPdf() {
        return "./results/images/large_image1.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/images/cmp_large_image1.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        PdfReader reader = new PdfReader(inputPdf);
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
        reader.close();
        // We create a new document with the correct size
        Document document = new Document(new Rectangle(img.getScaledWidth(), img.getScaledHeight()));
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        document.add(img);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new LargeImage1();
        test.makePdf();
    }
}
