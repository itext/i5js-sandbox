/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28236902/itext-combobox-width-of-selected-option-issue
 */
package sandbox.acroforms;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ComboBoxItems {

    class SelectCellEvent implements PdfPCellEvent {
        protected PdfFormField selectGroup;
        protected String name;
        protected String[] exports;
        protected String[] options;
        protected BaseFont font;
        
        public SelectCellEvent(PdfFormField selectGroup, String name, String[] exports, String[] options) throws DocumentException, IOException {
            this.selectGroup = selectGroup;
            this.name = name;
            this.exports = exports;
            this.options = options;
            font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
            font.setSubset(false);
        }
        
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            PdfWriter writer = canvases[0].getPdfWriter();
            TextField tf = new TextField(writer, position, name);
            tf.setFont(font);
            tf.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
            tf.setVisibility(TextField.VISIBLE_BUT_DOES_NOT_PRINT);
            tf.setBorderColor(BaseColor.GRAY);
            tf.setChoiceExports(exports);
            tf.setChoices(options);
            tf.setAlignment(Element.ALIGN_CENTER);
            try {
                selectGroup.addKid(tf.getComboField());
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }
    }
    
    public static final String DEST = "results/acroforms/combobox.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ComboBoxItems().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Rectangle pagesize = PageSize.LETTER;
        Document document = new Document(pagesize);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell;
        //Add rows with selectors
        PdfFormField selectGroup = PdfFormField.createEmpty(writer);
        selectGroup.setFieldName("myCombos");
        String[] options = {"Choose first option", "Choose second option", "Choose third option"};
        String[] exports = {"option1", "option2", "option3"};
        table.addCell("Combobox:");
        cell = new PdfPCell();
        cell.setCellEvent(new SelectCellEvent(selectGroup, "combo1", exports, options));
        cell.setMinimumHeight(20);
        table.addCell(cell);
        document.add(table);
        writer.addAnnotation(selectGroup);
        document.close();
    }

}
