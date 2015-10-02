/*
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30895930/issue-with-itext-radiocheckfield-when-displayed-on-multiple-pages
 */
package sandbox.acroforms;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class RadioGroupMultiPage2 {
    
    public static final String DEST = "results/acroforms/multipage_radiobutton2.pdf";
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RadioGroupMultiPage2().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfFormField radiogroup = PdfFormField.createRadioButton(writer, true);
        radiogroup.setFieldName("answer");
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell;
        for (int i = 0; i < 25; i++) {
            cell = new PdfPCell(new Phrase("Question " + i));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Answer " + i));
            table.addCell(cell);
        }
        for (int i = 0; i <25; i++) {
            cell = new PdfPCell(new Phrase("Radio: " + i));
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField(writer, radiogroup, "answer" + i));
            table.addCell(cell);
        }
        document.add(table);
        writer.addAnnotation(radiogroup);
        document.close();
    }
    
    class MyCellField implements PdfPCellEvent {

        protected PdfWriter writer;
        protected PdfFormField radiogroup;
        protected String value;
        
        public MyCellField(PdfWriter writer, PdfFormField radiogroup, String value) {
            this.writer = writer;
            this.radiogroup = radiogroup;
            this.value = value;
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            try {
                RadioCheckField radio = new RadioCheckField(writer, position, null, value);
                PdfFormField field = radio.getRadioField();
                writer.addAnnotation(field);
                radiogroup.addKid(field);
            } catch (IOException ex) {
                // do nothing
            } catch (DocumentException ex) {
                // do nothing
            }
        }
    }
}
