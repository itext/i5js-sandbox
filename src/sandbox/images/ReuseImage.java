package sandbox.images;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import sandbox.SandboxTest;

public class ReuseImage  extends SandboxTest {

    String inputPdf = "./resources/pdfs/single_image.pdf";

    @Override
    protected String getOutPdf() {
        return "./results/images/image_on_A4.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/images/cmp_image_on_A4.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        PdfReader reader = new PdfReader(inputPdf);
        // We assume that there's a single large picture on the first page
        PdfDictionary page = reader.getPageN(1);
        PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
        PdfDictionary xobjects = resources.getAsDict(PdfName.XOBJECT);
        PdfName imgRef = xobjects.getKeys().iterator().next();
        PRStream imgStream = (PRStream) xobjects.getAsStream(imgRef);
        // We now create a new Image object based on the bytes in the stream
        PdfImageObject imgObject = new PdfImageObject(imgStream);
        reader.close();
        Image img = Image.getInstance(imgObject.getImageAsBytes());
        img.scaleToFit(842, 595);
        img.setAbsolutePosition((842 - img.getScaledWidth()) / 2, (595 - img.getScaledHeight()) / 2);
        // We create a new document with the correct size
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(outPdf));
        document.open();
        document.add(img);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new ReuseImage();
        test.makePdf();
    }
}
