/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/31006683/itext-clickable-image-should-open-ms-word-attachment
 */
package sandbox.annotations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class FileAttachmentAnnot {
    public static final String DEST = "results/annotations/file_attachment.pdf";
    public static final String PATH = "resources/misc/test.docx";
    public static final String IMG = "resources/images/info.png";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FileAttachmentAnnot().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Rectangle rect = new Rectangle(36, 700, 136, 800);
        PdfFileSpecification fs = PdfFileSpecification.fileEmbedded(
                writer, PATH, "test.docx", null);
        PdfAnnotation attachment =
                PdfAnnotation.createFileAttachment(writer, rect, "Click me" , fs);
        PdfAppearance app = writer.getDirectContent().createAppearance(100, 100);
        Image img = Image.getInstance(IMG);
        img.scaleAbsolute(100, 100);
        img.setAbsolutePosition(0, 0);
        app.addImage(img);
        attachment.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, app);
        writer.addAnnotation(attachment);
        document.close();
    }
}
