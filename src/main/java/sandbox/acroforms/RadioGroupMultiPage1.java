/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30895930/issue-with-itext-radiocheckfield-when-displayed-on-multiple-pages
 */
package sandbox.acroforms;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class RadioGroupMultiPage1 {
    
    public static final String DEST = "results/acroforms/multipage_radiobutton1.pdf";
    /** Possible values of a Choice field. */
    public static final String[] LANGUAGES = { "English", "German", "French", "Spanish", "Dutch" };
 
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RadioGroupMultiPage1().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
    	// step 1
        Document document = new Document();
        // step 2
        PdfWriter writer =
            PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        // create a radio field spanning different pages
        PdfFormField radiogroup = PdfFormField.createRadioButton(writer, true);
        radiogroup.setFieldName("language");
        Rectangle rect = new Rectangle(40, 806, 60, 788);
        RadioCheckField radio;
        PdfFormField radiofield;
        for (int page = 0; page < LANGUAGES.length; ) {
            radio = new RadioCheckField(writer, rect, null, LANGUAGES[page]);
            radio.setBackgroundColor(new GrayColor(0.8f));
            radiofield = radio.getRadioField();
            radiofield.setPlaceInPage(++page);
            radiogroup.addKid(radiofield);
        }
        writer.addAnnotation(radiogroup);
        // add the content
        for (int i = 0; i < LANGUAGES.length; i++) {
            cb.beginText();
            cb.setFontAndSize(bf, 18);
            cb.showTextAligned(Element.ALIGN_LEFT, LANGUAGES[i], 70, 790, 0);
            cb.endText();
            document.newPage();
        }
        // step 5
        document.close();
    }
}
